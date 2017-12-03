package com.webcheckers.model;
import com.webcheckers.model.Exceptions.InvalidMoveException;

import java.util.ArrayList;

public class Game {
    //DATA FIELDS
    private CheckersBoard board;
    private Player player1;
    private Player player2;
    private int player1Pieces;                  //TODO LINK ALL UP BB
    private int player2Pieces;                  //TODO LINK ALL UP BB
    private String id;
    private long lastUpdateTime;
    private ArrayList<PastMove> moveHistory;      //TODO IMPLEMENT REVERSION OF MOVES/ATTACKS

    //STATE FIELDS
    private Player playerWon;
    private Player playerTurn;


/*  #######################################################################################################
    Constructor
    #######################################################################################################*/
    public Game(Player p1, Player p2, String id){
        this.player1 = p1;
        this.player2 = p2;
        this.player1Pieces = 12;
        this.player2Pieces = 12;
        this.id = id;
        this.board = new CheckersBoard(p1,p2);
        this.board.initBoard();
        this.lastUpdateTime = (long) (System.currentTimeMillis() / 1000L);      //Gets the current Unix Time
        this.moveHistory = new ArrayList<>();

        this.playerTurn = player1;
    }

/*  #######################################################################################################
    Getter Methods
    #######################################################################################################*/
    public String getId(){
        return this.id;
    }
    public Player getPlayer(int player){
        if(player == 1){
            return this.player1;
        }
        else if(player == 2){
            return this.player2;
        }
        return null;
    }
    public Player getPlayerTurn() {
        return playerTurn;
    }


/*  #######################################################################################################
    Public Methods
    #######################################################################################################*/


    public void playerResigned(Player p){
        if(p == this.player1){
            this.playerWon = player2;
        }
        else if(p == this.player2){
            this.playerWon = player1;
        }
        else{
            this.playerWon = null;
        }
    }
    /**
     * ------------------------------------------------------------------------------------------------------
     * playerWon
     *
     * Checks if a player has won and returns the player if they have, null otherwise
     * @return WinningPlayer/Null
     * ------------------------------------------------------------------------------------------------------
     */
    public Player playerWon(){
        if(this.player1Pieces == 0){
            return this.player2;
        }
        else if(this.player2Pieces == 0){
            return this.player1;
        }
        return null;
    }

    /**
     * ------------------------------------------------------------------------------------------------------
     * playTurn
     *
     * Plays a single turn in the game of checkers based on the player who wishes to move, the four numbers
     * of the coordinates of their move, and the type of move they wish to make.  It then saves the moves on
     * the stack
     * @param currPlayer
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @param type
     * ------------------------------------------------------------------------------------------------------
     */
    public void playTurn(Player currPlayer, int x0, int y0, int x1, int y1, MoveType type) throws InvalidMoveException{

        //TODO DOUBLE JUMPS
      if(type == MoveType.ATTACK){
            this.board.attack(x0, y0, x1, y1, currPlayer);
            if(currPlayer.equals(this.player1)){
                this.playerTurn = this.player2;
            }
            else if(currPlayer.equals(this.player2)){
                this.playerTurn = this.player1;
            }
        }

        if(type == MoveType.MOVE){
            this.board.move(x0, y0, x1, y1, currPlayer);
            if(currPlayer.equals(this.player1)){
                this.playerTurn = this.player2;
            }
            else if(currPlayer.equals(this.player2)) {
                this.playerTurn = this.player1;
            }
        }
        this.lastUpdateTime = (long) (System.currentTimeMillis() / 1000L);              //Update Last Update Time
        this.playerWon = playerWon();
        this.moveHistory.add(new PastMove(x0,y0,x1,y1,MoveType.MOVE,currPlayer));       //Add Move to Move History
    }

    /**
     * revertLastMove
     *
     * reverts the last move in the game stored in the Move History
     */
    public void revertLastMove(){
        PastMove lastMove = this.moveHistory.remove(this.moveHistory.size()-1);   //Removes last move from array
        //X0/X1/Y0/Y1 reversed so move is made backwards
        int x0 = lastMove.getX1();
        int y0 = lastMove.getY1();
        int x1 = lastMove.getX0();
        int y1 = lastMove.getY0();
        Player p = lastMove.getPlayerPastMove();
        this.board.uncheckedMove(x0,y0,x1,y1,p);
    }

/*  #######################################################################################################
    Communication Methods
    #######################################################################################################*/

    public String getGameAsString(Player player){
        String JSONfill = "";

        //ActivePlayer
        String activePlayer = "";
        if(playerTurn == player1){
            activePlayer = "1";
        }
        else{
            activePlayer = "2";
        }

        String playerWon = this.playerWon().getPlayerSessionId();

        //Player 1 ID/Name
        String player1ID = this.player1.getPlayerSessionId();
        String player1Name = this.player1.getName();

        //Player 2 ID/Name
        String player2ID = this.player2.getPlayerSessionId();
        String player2Name = this.player2.getName();

        //Game Board
        String board = this.getGameBoardAsString(player);

        //Past Moves
        String pastMoves = this.getMoveHistoryAsString();

        JSONfill = "{ "
                    + "\"activePlayer\": " + activePlayer + ", "
                    + "\"playerWon\": \"" + playerWon + "\", "
                    + "\"gameID\": \"" + this.getId() + "\", "
                    + "\"player1_ID\": \"" + player1ID + "\", "
                    + "\"player2_ID\": \"" + player2ID + "\", "
                    + "\"player1_Name\": \"" + player1Name + "\", "
                    + "\"player2_Name\": \"" + player2Name + "\", "
                    + "\"board\": " + board + ", "
                    + "\"moves\": " + pastMoves + " }";

        return JSONfill;
    }

    /**
     * -----------------------------------------------------------------------------------------------------
     * getGameBoardAsString(int playerNum)
     * 
     * Gets a JSON representation of the whole Game for sending to UI formatted to the perspective of
     * a player represented by an int
     * @return String JSONFill
     * ----------------------------------------------------------------------------------------------------
     */
    public String getGameBoardAsString(Player player){
        String JSONFill = "";
        if(player == player1){     // Player1 is requesting JSON
            CheckersBoard.space[][] boardArray = board.getPlayer1Board();
            JSONFill = get2DArrayAsJSON(boardArray);
        }
        else{                   // Player2 is requesting JSON
            CheckersBoard.space[][] boardArray = board.getPlayer2Board();
            JSONFill = get2DArrayAsJSON(boardArray);
        }
        return JSONFill;
    }


    public String getMoveHistoryAsString(){
        String JSONfill = "[ ";

        for(PastMove p : moveHistory){
            JSONfill = JSONfill.concat(p.getPastMoveAsString());
            JSONfill = JSONfill.concat(",");
        }
        if (JSONfill.length() > 4)
            JSONfill = JSONfill.substring(0, JSONfill.length() - 1);

        JSONfill = JSONfill.concat(" ]");

        return JSONfill;
    }

    /**
     * -----------------------------------------------------------------------------------------------------
     * get2DArrayAsJSON
     *
     * gets a JSON representation of a 2D array as a String
     * @param array
     * @return String JSONFILL
     * -----------------------------------------------------------------------------------------------------
     */
    private static String get2DArrayAsJSON(CheckersBoard.space[][] array) {
        String JSONFill = "[";
        for (int y = 0; y < 8; y++) {
            JSONFill += "[";
            for (int x = 0; x < 8; x++) {
                JSONFill += array[x][y].ordinal();
                if (x < 8 - 1) {
                    JSONFill += ",";
                }
            }
            JSONFill += "]";
            if (y < 8 - 1) {
                JSONFill += ",";
            }
        }
        JSONFill = JSONFill + "]";
        return JSONFill;
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     * getSimpleGameAsString()
     * 
     * Returns String representation of the Game for the JSON file.
     * @return
     * ----------------------------------------------------------------------------------------------------
     */
    public String getSimpleGameAsString(){
        String game = "{\"id\": \"" + this.id +
        "\", \"playerOneUsername\": \"" + this.player1.getName() +
        "\", \"playerTwoUsername\": \"" + this.player2.getName() + "\"}";
        return game;
    }

    public static void main(String args[]){
        Player p1 = new Player("123","Peepee");
        Player p2 = new Player("123","PooPoo");
        Game g = new Game(p1, p2, "GAME_ID");
        System.out.print(g.getSimpleGameAsString());
    }
}

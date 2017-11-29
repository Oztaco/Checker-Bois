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
    private ArrayList<PastMove> MoveHistory;      //TODO IMPLEMENT REVERSION OF MOVES/ATTACKS

    //STATE FIELDS
    private Player playerWon;
    private Player playerTurn;


/*  #######################################################################################################
    Constructor
    #######################################################################################################*/
    public Game(Player p1, Player p2, String id){
        this.player1 = p1;
        this.player2 = p2;
        this.id = id;
        this.board = new CheckersBoard(p1,p2);
        this.board.initBoard();
        this.lastUpdateTime = (long) (System.currentTimeMillis() / 1000L);      //Gets the current Unix Time
        this.MoveHistory = new ArrayList<>();

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


/*  #######################################################################################################
    Public Methods
    #######################################################################################################*/

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
    public void playTurn(Player currPlayer, int x0, int y0, int x1, int y1, MoveType type){

        //TODO MAKE ATTACK WORK
/*      if(type == MoveType.ATTACK){
            try{
                this.board.attack(x0, y0, x1, y1, currPlayer);
                if(currPlayer.equals(this.player1)){
                    this.playerTurn = this.player2;
                }
                else if(currPlayer.equals(this.player2)){
                    this.playerTurn = this.player1;
                }
            }
            catch(InvalidMoveException e) {
                //Do nothing, game is not edited
                //Prolly have some Error message eventually
            }
        }                                                   */

        if(type == MoveType.MOVE){
            try{
                this.board.move(x0, y0, x1, y1, currPlayer);
                if(currPlayer.equals(this.player1)){
                    this.playerTurn = this.player2;
                }
                else if(currPlayer.equals(this.player2)){
                    this.playerTurn = this.player1;
                }
            }
            catch(InvalidMoveException e) {
                //Do nothing, game is not edited
                //Prolly have some Error message eventually
            }
        }
        this.lastUpdateTime = (long) (System.currentTimeMillis() / 1000L);              //Update Last Update Time
        this.playerWon = playerWon();
        this.MoveHistory.add(new PastMove(x0,y0,x1,y1,MoveType.MOVE,currPlayer));       //Add Move to Move History
    }

    /**
     * revertLastMove
     *
     * reverts the last move in the game stored in the Move History
     */
    public void revertLastMove(){
        PastMove lastMove = this.MoveHistory.remove(this.MoveHistory.size()-1);   //Removes last move from array
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
                JSONFill += array[x][y];
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

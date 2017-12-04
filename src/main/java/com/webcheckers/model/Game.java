package com.webcheckers.model;
import com.webcheckers.model.Exceptions.InvalidMoveException;
import javafx.scene.control.cell.CheckBoxListCell;

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
    private boolean multiAttack;

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
        this.multiAttack = false;

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
        // Did someone already resign?
        if (this.playerWon == null) {
            return;
        }
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

    public boolean checkCorners(int x0, int y0, CheckersBoard.space me, CheckersBoard.space meKing){
        return (this.board.getCoords(x0+1,y0+1) == CheckersBoard.space.EMPTY
                    || this.board.getCoords(x0+1,y0+1) == me
                    || this.board.getCoords(x0+1,y0+1) == meKing) //Check if Bottom Right is Empty or controlled by same player
                && (this.board.getCoords(x0+1,y0-1) == CheckersBoard.space.EMPTY
                    || this.board.getCoords(x0+1,y0-1) == me
                    || this.board.getCoords(x0+1,y0-1) == meKing) //Check if Top Right is Empty or controlled by same player
                && (this.board.getCoords(x0-1,y0+1) == CheckersBoard.space.EMPTY
                    || this.board.getCoords(x0-1,y0+1) == me
                    || this.board.getCoords(x0-1,y0+1) == meKing) //Check if Bottom LeftRight is Empty or controlled by same player
                && (this.board.getCoords(x0-1,x0-1) == CheckersBoard.space.EMPTY
                    || this.board.getCoords(x0-1,y0-1) == me
                    || this.board.getCoords(x0-1,y0-1) == meKing);
    }

    public boolean checkCornersOther(int x0, int y0, CheckersBoard.space other, CheckersBoard.space otherKing){
        return (this.board.getCoords(x0+1,y0+1) == other
                    && this.board.getCoords(x0+2, y0+2) == CheckersBoard.space.EMPTY)
                && (this.board.getCoords(x0+1,y0-1) == other
                    && this.board.getCoords(x0+2, y0-2) == CheckersBoard.space.EMPTY)
                && (this.board.getCoords(x0-1,y0+1) == other
                    && this.board.getCoords(x0-2, y0+2) == CheckersBoard.space.EMPTY)
                && (this.board.getCoords(x0-1,x0-1) == other
                    && this.board.getCoords(x0-2, y0-2) == CheckersBoard.space.EMPTY);
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
        //setup me and others
        CheckersBoard.space me = null;
        CheckersBoard.space meKing = null;
        CheckersBoard.space other = null;
        CheckersBoard.space otherKing = null;
        if(currPlayer == this.player1){
            me = CheckersBoard.space.PLAYER1;
            meKing = CheckersBoard.space.PLAYER1KING;
            other = CheckersBoard.space.PLAYER2;
            otherKing = CheckersBoard.space.PLAYER2KING;
        }
        else if(currPlayer == this.player2){
            me = CheckersBoard.space.PLAYER2;
            meKing = CheckersBoard.space.PLAYER2KING;
            other = CheckersBoard.space.PLAYER1;
            otherKing = CheckersBoard.space.PLAYER1KING;
        }

        //Handle Attacks
        if(type == MoveType.ATTACK){
            if(this.multiAttack == false){           //Not Player's first jump thus "turn"
                this.board.attack(x0, y0, x1, y1, currPlayer);
                this.moveHistory.add(new PastMove(x0, y0, x1, y1, type, currPlayer));
            }
            else if(this.multiAttack == true){     //Players first jump this "turn"
                if(checkLastInMoveHistory(x0,y0)) {
                    this.board.attack(x0, y0, x1, y1, currPlayer);
                    this.moveHistory.add(new PastMove(x0, y0, x1, y1, type, currPlayer));
                }
                else{
                    //Invalid to double jump with a different piece
                    throw(new InvalidMoveException("You fuck fucked it fuck"));
                }
            }
            if(currPlayer.equals(this.player1)){
                this.player1Pieces --;
            }
            else if(currPlayer.equals(this.player2)){
                this.player2Pieces--;
            }
        }
        //Handle Moves
        else if(type == MoveType.MOVE){
            this.board.move(x0,y0,x1,y1,currPlayer);
            this.moveHistory.add(new PastMove(x0, y0, x1, y1, type, currPlayer));
        }
        chooseNext(x1, y1, currPlayer, type);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * Check last move in history
     *
     * checks the last move and history, returns true if the X, Y given are the same as the end of last move
     * @param x0
     * @param y0
     * @return
     * -----------------------------------------------------------------------------------------------------------------
     */
    public boolean checkLastInMoveHistory(int x0, int y0){
        return (this.moveHistory.get(moveHistory.size()-1).getX1() == x0
                && this.moveHistory.get(moveHistory.size()-1).getY1() == y0);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * chooseNext
     *
     * Chooses the next player to play and stores it in the field
     * @param x1
     * @param y1
     * @param currPlayer
     * @param type
     * -----------------------------------------------------------------------------------------------------------------
     */
    public void chooseNext(int x1, int y1, Player currPlayer, MoveType type){
        if(type == MoveType.MOVE){
            if(currPlayer.equals(this.player1)){
                //Player 1 just played
                this.playerTurn = player2;
            }
            else if(currPlayer.equals(this.player2)){
                //Player 2 just played
                this.playerTurn = player1;
            }
        }
        else {
            if(currPlayer.equals(this.player1)){
                if(checkAttackCoordsP1(x1,y1,CheckersBoard.space.PLAYER2, this.board.getCoords(x1,y1))){
                    this.playerTurn = this.player1; //Player 1 Turn remains if there is a valid attack to make
                    this.multiAttack = true;
                }
                else{
                    this.playerTurn = this.player2;
                    this.multiAttack = false;
                }
            }
            else if(currPlayer.equals(this.player2)){
                if(checkAttackCoordsP2(x1,y1,CheckersBoard.space.PLAYER1, this.board.getCoords(x1,y1))){
                    this.playerTurn = this.player2; //Player 2 Turn remains if there is a valid attack to make
                    this.multiAttack = true;
                }
                else{
                    this.playerTurn = this.player1;
                    this.multiAttack = false;
                }
            }
        }
    }

    public boolean checkAttackCoordsP1(int x1, int y1, CheckersBoard.space space, CheckersBoard.space mySpace){
        if(mySpace == CheckersBoard.space.PLAYER1KING){
            return (((x1+1 < 8 && x1+1 > 0 && y1+1 < 8 && y1+1 > 0) &&
                        (this.board.getCoords(x1+1,y1+1) == space
                            && this.board.getCoords(x1+2, y1+2) == CheckersBoard.space.EMPTY))
                    || ((x1+1 < 8 && x1+1 > 0 && y1-1 < 8 && y1-1 > 0) &&
                        (this.board.getCoords(x1+1,y1-1) == space
                            && this.board.getCoords(x1+2, y1-2) == CheckersBoard.space.EMPTY))
                    || ((x1-1 < 8 && x1-1 > 0 && y1+1 < 8 && y1+1 > 0) &&
                        (this.board.getCoords(x1-1,y1+1) == space
                            && this.board.getCoords(x1-2, y1+2) == CheckersBoard.space.EMPTY))
                    || ((x1-1 < 8 && x1-1 > 0 && y1-1 < 8 && y1-1 > 0) &&
                        (this.board.getCoords(x1-1,y1-1) == space
                            && this.board.getCoords(x1+2, y1-2) == CheckersBoard.space.EMPTY)));
        }
        return (((x1+1 < 8 && x1+1 > 0 && y1+1 < 8 && y1+1 > 0) &&
                    (this.board.getCoords(x1+1,y1+1) == space
                        && this.board.getCoords(x1+2, y1+2) == CheckersBoard.space.EMPTY))
                || ((x1-1 < 8 && x1-1 > 0 && y1+1 < 8 && y1+1 > 0) &&
                    (this.board.getCoords(x1-1,y1+1) == space
                        && this.board.getCoords(x1-2, y1+2) == CheckersBoard.space.EMPTY)));

    }

    public boolean checkAttackCoordsP2(int x1, int y1, CheckersBoard.space space, CheckersBoard.space mySpace){
        if(mySpace == CheckersBoard.space.PLAYER2KING){
            return (((x1+1 < 8 && x1+1 > 0 && y1+1 < 8 && y1+1 > 0) &&
                    (this.board.getCoords(x1+1,y1+1) == space
                            && this.board.getCoords(x1+2, y1+2) == CheckersBoard.space.EMPTY))
                    || ((x1+1 < 8 && x1+1 > 0 && y1-1 < 8 && y1-1 > 0) &&
                    (this.board.getCoords(x1+1,y1-1) == space
                            && this.board.getCoords(x1+2, y1-2) == CheckersBoard.space.EMPTY))
                    || ((x1-1 < 8 && x1-1 > 0 && y1+1 < 8 && y1+1 > 0) &&
                    (this.board.getCoords(x1-1,y1+1) == space
                            && this.board.getCoords(x1-2, y1+2) == CheckersBoard.space.EMPTY))
                    || ((x1-1 < 8 && x1-1 > 0 && y1-1 < 8 && y1-1 > 0) &&
                    (this.board.getCoords(x1-1,y1-1) == space
                            && this.board.getCoords(x1+2, y1-2) == CheckersBoard.space.EMPTY)));
        }
        return (((x1+1 < 8 && x1+1 > 0 && y1-1 < 8 && y1-1 > 0) &&
                    (this.board.getCoords(x1+1,y1-1) == space
                        && this.board.getCoords(x1+2, y1-2) == CheckersBoard.space.EMPTY))
                || ((x1-1 < 8 && x1-1 > 0 && y1-1 < 8 && y1-1 > 0) &&
                    (this.board.getCoords(x1-1,y1-1) == space
                        && this.board.getCoords(x1-2, y1-2) == CheckersBoard.space.EMPTY)));
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * revertLastMove
     *
     * reverts the last move in the game stored in the Move History
     * -----------------------------------------------------------------------------------------------------------------
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

        String playerWon;
        if (this.playerWon() != null)
            playerWon = this.playerWon().getPlayerSessionId();
        else
            playerWon = "-1";

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

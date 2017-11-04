package com.webcheckers.model;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.webcheckers.model.Exceptions.InvalidMoveException;

public class CheckersBoard {

    public enum space{
        EMPTY,
        INVALID,
        PLAYER1,
        PLAYER2,
        PLAYER1KING,
        PLAYER2KING;
    }
    private space[][] board;
    private Player player1;
    private Player player2;

    /**
     * Constructor for a board.  Uses two players as input
     * @param username1
     * @param username2
     */
    public CheckersBoard(Player username1, Player username2){
        this.board = new space[8][8];
        player1 = username1;
        player2 = username2;
    }

    /**
     * Initializes the board, filling it with enums
     * corresponding to the contents of the spaces.
     */
    public void initBoard(){
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                //player 1 side
                if(y == 0 || y == 2){
                    if(x % 2 == 0){
                        board[y][x] = space.INVALID;
                    }
                    else{
                        board[y][x] = space.PLAYER1;
                    }
                }
                else if(y == 1){
                    if(x % 2 == 0){
                        board[y][x] = space.PLAYER1;
                    }
                    else{
                        board[y][x] = space.INVALID;
                    }
                }

                //middle area
                else if(y == 3){
                    if(x % 2 == 0){
                        board[y][x] = space.EMPTY;
                    }
                    else{
                        board[y][x] = space.INVALID;
                    }
                }
                else if(y == 4){
                    if(x % 2 == 0){
                        board[y][x] = space.INVALID;
                    }
                    else{
                        board[y][x] = space.EMPTY;
                    }
                }

                //player 2 side
                if(y == 5 || y == 7){
                    if(x % 2 == 0){
                        board[y][x] = space.PLAYER2;
                    }
                    else{
                        board[y][x] = space.INVALID;
                    }
                }
                else if (y==6){   //y == 6
                    if(x % 2 == 0){
                        board[y][x] = space.INVALID;
                    }
                    else{
                        board[y][x] = space.PLAYER2;
                    }
                }
            }
        }
    }

    /**
     * Prints the board for testing purposes
     */
    public void printBoard(){
        System.out.println("");
        String val;

        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if(board[y][x] == space.INVALID){
                    val = ".";
                }
                else if(board[y][x] == space.EMPTY){
                    val = " ";
                }
                else if(board[y][x] == space.PLAYER1){
                    val = "1";
                }
                else if(board[y][x] == space.PLAYER2){
                    val = "2";
                }
                else if(board[y][x] == space.PLAYER1KING){
                    val = "A";
                }
                else{
                    val = "B";
                }
                System.out.print("[" + val + "]");
            }
            System.out.println("");
        }
    }

    /**
     * Kings the piece at x,y if it is not already a king.  Does nothing otherwise.
     * @param x
     * @param y
     */
    public void kingPiece(int x, int y){
        if(this.board[y][x] == space.PLAYER1){
            this.board[y][x] = space.PLAYER1KING;
        }
        else if(this.board[y][x] == space.PLAYER2) {
            this.board[y][x] = space.PLAYER2KING;
        }
    }

    /**
     * Moves a piece for the player specified.
     *
     * Checks:
     *      Whether the piece moved belongs to the correct player
     *      Whether the piece moved
     *
     * @param x0, y0, x1, y1, player
     */
    public void move(int x0, int y0, int x1, int y1, Player player) throws InvalidMoveException {

        //Setup changInX and changeInY for determining validity later
        int changeInX = x1-x0;
        int changeInY = y1-y0;
        space me = null;
        space meKing = null;

        //Set me and meKing to the current Player Enums
        if(player.equals(this.player1)){
            me = space.PLAYER1;
            meKing = space.PLAYER1KING;
        }

        else if(player.equals(this.player2)){
            me = space.PLAYER2;
            meKing = space.PLAYER2KING;
        }

        //Throw exception if a player tries to move the piece of the other player
        if(!(board[y0][x0] == me || board[y0][x0] == meKing)){
            throw new InvalidMoveException("The contents of the tile do not match the player trying to play");
        }

        //Throw exception if Moves have a distance of 1 from the original space
        if(Math.abs(changeInX) != 1 || Math.abs(changeInY) != 1) {
            throw new InvalidMoveException("Moves must be a distance of 1 from the piece");
        }

        else {
            if(me == space.PLAYER1KING){                //If space has a Player 1 King on tile, register move
                if (board[y1][x1] == space.EMPTY) {     //if space is not occupied, exception otherwise
                    space tempSpace = this.board[y0][x0];
                    this.board[y0][x0] = space.EMPTY;
                    this.board[y1][x1] = tempSpace;
                } else {
                    throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                }
            }
            else if(me == space.PLAYER2KING){           //If space has a Player 2 King on tile, register move
                if (board[y1][x1] == space.EMPTY) {     //if space is not occupied, exception otherwise
                    space tempSpace = this.board[y0][x0];
                    this.board[y0][x0] = space.EMPTY;
                    this.board[y1][x1] = tempSpace;
                } else {
                    throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                }
            }
            else if(me == space.PLAYER1){               //If space has a Player 1 on tile, register move
                if(changeInY > 0){                      //if player is moving in the appropriate direction
                    if (board[y1][x1] == space.EMPTY) { //and if space if not occupied
                        space tempSpace = this.board[y0][x0];
                        this.board[y0][x0] = space.EMPTY;
                        this.board[y1][x1] = tempSpace;
                    } else {
                        throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                    }
                }
                else {
                    throw new InvalidMoveException("Only kings can move backwards");
                }
            }
            else if(me == space.PLAYER2){               //If space has a Player 2 on tile, register move
                if(changeInY < 0){                      //if player is moving in the appropriate direction
                    if (board[y1][x1] == space.EMPTY) { //and if space if not occupied
                        space tempSpace = this.board[y0][x0];
                        this.board[y0][x0] = space.EMPTY;
                        this.board[y1][x1] = tempSpace;
                    } else {
                        throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                    }
                }
                else {
                    throw new InvalidMoveException("Only kings can move backwards");
                }
            }
        }

        //Check if any players should be kinged after a move
        for(int x = 0; x < 8; x++){
            if(this.board[0][x] == space.PLAYER2){
                kingPiece(x,0);     //King piece located at
            }
            else if(this.board[7][x] == space.PLAYER1){
                kingPiece(x,7);     //King piece located at
            }
        }
    }

    /**
     * Gets the player based the number input
     * @param player
     * @return Player player, NULL
     */
    public Player getPlayer(int player){
        if(player == 1){
            return player1;
        }
        else if(player == 2){
            return player2;
        }
        return null;
    }

    public static void main(String args[]) throws InvalidMoveException {


        //-------------------------------------------------------------------------------------------------------------
        /**
         * MOVE TESTS
         *      Tests Move method
         *          1.1     Tests valid move player 1
         *          1.2     Tests Backwards Move player 1 (InvalidMoveException)
         *          2       Tests move when wrong player is called (InvalidMoveException)
         *          3       Tests move when the player tries to move an invalid distance (InvalidMoveException)
         *          4       Tests move into an occupied space (InvalidMoveException)
         *          5       Tests move into an invalid space (InvalidMoveException)
         *          6.1     Valid Move Player 2
         *          6.2     Backwards move Player 2 (Invalid Move Exception)
         *          7       Invalid Move Player 2 (InvalidMoveException)
         *          //TODO  ADD TESTS FOR:
         *              Valid Move Player1King
         *              Valid Move Player2King
         *              Move that kings Player 1
         *              Move that kings Player 2
         */

        //TESTS 1
        //-------------------------------------------------------------------------------------------------------------

        CheckersBoard cb1 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb1.initBoard();
        System.out.println("---------------------BOARD 1---------------------");
        System.out.println("Test 1.1: Player 1 makes a Valid Move");
        cb1.printBoard();
        try{
            cb1.move(1,2, 0,3, cb1.getPlayer(1));
            System.out.println("STATUS: PASSED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: FAILED");
        }


        System.out.println("\nTest 1.2: Player 1 Moves Backwards");
        cb1.printBoard();
        try{
            cb1.move(0,3,1,2,cb1.getPlayer(1));
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 2
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb2 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb2.initBoard();
        System.out.println("\n---------------------BOARD 2---------------------");
        System.out.println("Test 2: Throws Error when player != player at tile");
        cb2.printBoard();
        try{
            cb2.move(1,2, 0,3, cb2.player2);
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 3
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb3 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb3.initBoard();
        System.out.println("\n---------------------BOARD 3---------------------");
        System.out.println("Test 3: Throws Error when distance to next move > 1");
        cb3.printBoard();
        try{
            cb3.move(1,2, 1,4, cb3.player1);
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }

        //TESTS 4
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb4 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb4.initBoard();
        System.out.println("\n---------------------BOARD 4---------------------");
        System.out.println("Test 4: Throws Error when space is occupied");
        cb4.printBoard();
        try{
            cb4.move(1,0, 0,1, cb4.player1);
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 5
        //--------------------------------------------------------------------------------------------------------------


        CheckersBoard cb5 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb5.initBoard();
        System.out.println("\n---------------------BOARD 5---------------------");
        System.out.println("Test 5: Throws Error when space is invalid");
        cb5.printBoard();
        try{
            cb5.move(1,0, 1,1, cb5.player1);
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 6
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb6 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb6.initBoard();
        System.out.println("\n---------------------BOARD 6---------------------");
        System.out.println("Test 6.1: Player 2 Makes A Valid Move");
        cb6.printBoard();
        try{
            cb6.move(0,5, 1,4, cb6.getPlayer(2));
            System.out.println("STATUS: PASSED");
        }
        catch(InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS: FAILED");
        }

        System.out.println("Test 6.2: Player 2 Moves Backwards");
        cb6.printBoard();
        try{
            cb6.move(1,4,0,5,cb1.getPlayer(2));
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 7
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb7 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb7.initBoard();
        System.out.println("\n---------------------BOARD 7---------------------");
        System.out.println("Test 7: Player 2 makes an invalid move");
        cb7.printBoard();
        try{
            cb7.move(0,5,0,4,cb7.getPlayer(2));
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }


        //-------------------------------------------------------------------------------------------------------------


    }
}

package com.webcheckers.model;

import javax.xml.bind.SchemaOutputResolver;

public class CheckersBoard {

    public enum space{
        EMPTY,
        INVALID,
        PLAYER1,
        PLAYER2,
        PLAYER1KING,
        PLAYER2KING;
    }
    public enum moveType{
        move,
        attack
    }
    private space[][] board;
    private Player player1;
    private Player player2;


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
     * Prints the board for testing purposes.
     */
    public void printBoard(){
        System.out.println("");
        String val;

        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if(board[y][x] == space.INVALID){
                    val = "X";
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
     * Moves a piece for the player specified
     *
     * //TODO reverse indexing
     * @param
     */
    public void move(int x0, int y0, int x1, int y1, Player player, moveType movetype) throws InvalidMoveException{
        int changeInX = 0;
        int changeInY = 0;
        boolean greaterx1 = false;
        boolean greatery1 = false;
        space other = null;
        space otherKing = null;
        space me = null;
        space meKing = null;

        if(player.equals(this.player1)){
            other = space.PLAYER2;
            otherKing = space.PLAYER2KING;
            me = space.PLAYER1;
            meKing = space.PLAYER1KING;
        }
        else if(player.equals(this.player2)){
            other = space.PLAYER1;
            otherKing = space.PLAYER1KING;
            me = space.PLAYER2;
            meKing = space.PLAYER2KING;
        }

        if(x1 > x0){
            greaterx1 = true;
            changeInX = x1 - x0;
            if(y1 > y0){
                greatery1 = true;
                changeInY = y1 - y0;
            }
            else if(y1 < y0){
                changeInY = y0 - y1;
            }
        }
        else if(x1 < x0) {
            changeInX = x0 - x1;
            if (y1 > y0) {
                greatery1 = true;
                changeInY = y1 - y0;
            } else if (y1 < y0) {
                changeInY = y0 - y1;
            }
        }
        if(!(board[y0][x0] == me || board[y0][x0] == meKing)){
            throw new InvalidMoveException("The contents of the tile do not match the player trying to play");
        }

        if(movetype == moveType.move){
            if(changeInX != 1 && changeInY != 1){
                throw new InvalidMoveException("Moves must be a distance of 1 from the piece");
            }
            else{
                if(board[y1][x1] == space.EMPTY){
                    space tempSpace = this.board[y0][x0];
                    this.board[y0][x0]= space.EMPTY;
                    this.board[y1][x1] = tempSpace;
                }
                else{
                    throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                }
            }
        }
        //TODO fix movetype attack
//        else if(movetype == moveType.attack){
//            if(changeInX != 2 && changeInY != 2){
//                throw new InvalidMoveException("Attacks must be a distance of 2 from the piece");
//            }
//            else{
//                if(greaterx1 && greatery1){
//                    if(this.board[x0+1][y0+1] == other || this.board[x0+1][y0+1] == otherKing){
//                        space tempSpace = this.board[x0][y0];
//                        this.board[x0][y0]= space.EMPTY;
//                        this.board[x1][y1] = tempSpace;
//                    }
//                    else{
//                        throw new InvalidMoveException("There is no enemy piece in your path");
//                    }
//                }
//                else if(greaterx1 && !greatery1){
//                    if(this.board[x0+1][y0-1] == other || this.board[x0+1][y0-1] == otherKing){
//                        space tempSpace = this.board[x0][y0];
//                        this.board[x0][y0]= space.EMPTY;
//                        this.board[x1][y1] = tempSpace;
//                    }
//                    else{
//                        throw new InvalidMoveException("There is no enemy piece in your path");
//                    }
//                }
//                else if(!greaterx1 && greatery1){
//                    if(this.board[x0-1][y0+1] == other || this.board[x0-1][y0+1] == otherKing){
//                        space tempSpace = this.board[x0][y0];
//                        this.board[x0][y0]= space.EMPTY;
//                        this.board[x1][y1] = tempSpace;
//                    }
//                    else{
//                        throw new InvalidMoveException("There is no enemy piece in your path");
//                    }
//
//                }
//                else if(!greaterx1 && !greatery1){
//                    if(this.board[x0-1][y0-1] == other || this.board[x0-1][y0-1] == otherKing){
//                        space tempSpace = this.board[x0][y0];
//                        this.board[x0][y0]= space.EMPTY;
//                        this.board[x1][y1] = tempSpace;
//
//                    }
//                    else{
//                        throw new InvalidMoveException("There is no enemy piece in your path");
//                    }
//                }
//
//           }
//        }
    }


    /**
     * Performs an attack, where the current player's
     * piece leaps over an enemy piece, which is then
     * removed from the board.
     *
     * //TODO reverse indexing
     * @param x0 - initial x position
     * @param y0 - initial y position
     * @param x1 - final x position
     * @param y1 - final y position
     * @param player - player making the move
     */
    public void attack(int x0, int y0, int x1, int y1, Player player) throws InvalidMoveException {
        if (board[y1][x1] == space.EMPTY) { //desired space is empty
            if (board[y0][x0] == space.PLAYER1 && player == player1) { //player 1 is trying to move their piece

                if (y1 < y0) { //moving the piece in the correct direction
                    if(x1 < x0){ //piece is moving left on the board
                        if(board[y0+1][x0-1] == space.PLAYER2 || board[y0+1][x0-1] == space.PLAYER2KING){ //jumped space is occupied by an enemy piece
                            board[y0][x0] = space.EMPTY;
                            board[y1][x1] = space.PLAYER1;
                            board[y0+1][x0-1] = space.EMPTY;
                        }
                    }
                    else if(x1 > x0){ //piece is moving right on the board
                        if(board[y0+1][x0+1] == space.PLAYER2 || board[y0+1][x0+1] == space.PLAYER2KING){ //jumped space is occupied by an enemy piece
                            board[y0][x0] = space.EMPTY;
                            board[y1][x1] = space.PLAYER1;
                            board[y0+1][x0+1] = space.EMPTY;
                        }
                    }
                    else{ //x1 == x0?
                        throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                    }
                }
                else { //y1 >= y0
                    throw new InvalidMoveException("This piece cannot move in this direction");
                }
            }

            else if (board[y0][x0] == space.PLAYER2 && player == player2) { //player 2 is trying to move their piece
                if (y1 > y0) { //moving piece in the correct direction
                    if(x1 < x0){ //piece is moving left on the board
                        if(board[y0-1][x0-1] == space.PLAYER1 || board[y0-1][x0-1] == space.PLAYER1KING){ //jumped space is occupied by an enemy piece
                            board[y0][x0] = space.EMPTY;
                            board[y1][x1] = space.PLAYER2;
                            board[y0-1][x0-1] = space.EMPTY;
                        }
                    }
                    else if(x1 > x0){ //piece is moving right on the board
                        if(board[y0-1][x0+1] == space.PLAYER1 || board[y0-1][x0+1] == space.PLAYER1KING){ //jumped space is occupied by an enemy piece
                            board[y0][x0] = space.EMPTY;
                            board[y1][x1] = space.PLAYER2;
                            board[y0-1][x0+1] = space.EMPTY;
                        }
                    }
                    else{ //x1 == x0?
                        throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                    }
                }
                else { //y1 <= y0
                    throw new InvalidMoveException("This piece cannot move in this direction");
                }
            }

            //handle king movement

            else {
                throw new InvalidMoveException("The contents of the tile do not match the player trying to play");
            }
        }
        else {
            throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
        }
    }

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
        CheckersBoard cb1 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb1.initBoard();
        System.out.println("---------------------BOARD 1---------------------");
        System.out.println("Test1: Prove Move Works");
        cb1.printBoard();
        cb1.move(1,2, 0,3, cb1.player1, moveType.move);
        cb1.printBoard();


        CheckersBoard cb2 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb2.initBoard();
        System.out.println("\n---------------------BOARD 2---------------------");
        System.out.println("Test2: Throws Error when player != player at tile");
        cb2.printBoard();
        try{
            cb2.move(1,2, 0,3, cb2.player2, moveType.move);
        }
        catch(InvalidMoveException e){
            System.out.println("Error Caught: \"The contents of the tile do not match the player trying to play\"");
        }


        CheckersBoard cb3 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb3.initBoard();
        System.out.println("\n---------------------BOARD 3---------------------");
        System.out.println("Test3: Throws Error when distance to next move > 1");
        cb3.printBoard();
        try{
            cb3.move(1,2, 1,4, cb2.player1, moveType.move);
        }
        catch(InvalidMoveException e){
            System.out.println("Error Caught: \"Moves must be a distance of 1 from the piece\"");
        }


        CheckersBoard cb4 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb4.initBoard();
        System.out.println("\n---------------------BOARD 4---------------------");
        System.out.println("Test4: Throws Error when space is occupied");
        cb4.printBoard();
        try{
            cb3.move(1,0, 0,1, cb2.player1, moveType.move);
        }
        catch(InvalidMoveException e){
            System.out.println("Error Caught: \"Space you want to move to is Occupied or Invalid\"");
        }

        CheckersBoard cb5 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb5.initBoard();
        System.out.println("\n---------------------BOARD 5---------------------");
        System.out.println("Test5: Throws Error when space is invalid");
        cb5.printBoard();
        try{
            cb3.move(1,0, 1,1, cb2.player1, moveType.move);
        }
        catch(InvalidMoveException e){
            System.out.println("Error Caught: \"Space you want to move to is Occupied or Invalid\"");
        }

    }
}

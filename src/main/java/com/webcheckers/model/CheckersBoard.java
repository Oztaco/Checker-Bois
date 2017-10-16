package com.webcheckers.model;

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
        attack;
    }
    private space[][] board;
    private Player player1;
    private Player player2;


    private CheckersBoard(){
        this.board = new space[8][8];
        player1 = new Player();
        player1.setupPlayer();
        player2 = new Player();
        player2.setupPlayer();
    }

    /**
     * Initializes the board, filling it with enums
     * corresponding to the contents of the spaces.
     */
    private void initBoard(){
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
    private void printBoard(){
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
     * @param
     */
    public void move(int x0, int y0, int x1, int y1, Player player, moveType movetype) throws InvalidMoveException{
        int changeInX = 0;
        int changeInY = 0;
        boolean greaterx1 = false;
        boolean greatery1 = false;
        space other = null;
        space otherKing = null;

        if(player.equals(this.player1)){
            other = space.PLAYER2;
            otherKing = space.PLAYER2KING;
        }
        else if(player.equals(this.player2)){
            other = space.PLAYER1;
            otherKing = space.PLAYER1KING;
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

        if(movetype == moveType.move){
            if(changeInX != 1 && changeInY != 1){
                throw new InvalidMoveException("Moves must be a distance of 1 from the piece!");
            }
            else{
                if(board[x1][y1] == space.PLAYER1);
            }
        }
        else if(movetype == moveType.attack){
            if(changeInX != 2 && changeInY != 2){
                throw new InvalidMoveException("Attacks must be a distance of 2 from the piece!");
            }
            else{
                if(greaterx1 && greatery1){

                }
                else if(greaterx1 && !greatery1){

                }
                else if(!greaterx1 && greatery1){

                }
                else if(!greaterx1 && !greatery1){
                    if(this.board[x0-1][x0-1] == other || this.board[x0-1][x0-1] == otherKing){

                    }
                }
            }
        }
    }

    public static void main(String args[]){
        CheckersBoard cb1 = new CheckersBoard();
        cb1.initBoard();
        cb1.printBoard();
    }
}

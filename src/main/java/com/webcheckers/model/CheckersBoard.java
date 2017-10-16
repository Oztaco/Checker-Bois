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
    private space[][] board;
    private Player player1;
    private Player player2;


    private CheckersBoard(){
        this.board = new space[8][8];
        player1 = new Player();
        player2 = new Player();
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
                else{   //y == 6
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

    public static void main(String args[]){
        CheckersBoard cb1 = new CheckersBoard();
        cb1.initBoard();
        cb1.printBoard();
    }
}

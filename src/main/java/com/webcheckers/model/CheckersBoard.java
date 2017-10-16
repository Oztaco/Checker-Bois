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


    public CheckersBoard(Player username1, Player username2){
        this.board = new space[8][8];
        player1 = username1;
        player2 = username2;
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
                throw new InvalidMoveException("Moves must be a distance of 1 from the piece");
            }
            else{
                if(board[y1][x1] == space.EMPTY){
                    space tempSpace = this.board[x0][y0];
                    this.board[y0][x0]= space.EMPTY;
                    this.board[y1][x1] = tempSpace;
                }
                else{
                    throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                }
            }
        }
        else if(movetype == moveType.attack){
            if(changeInX != 2 && changeInY != 2){
                throw new InvalidMoveException("Attacks must be a distance of 2 from the piece");
            }
            else{
                if(greaterx1 && greatery1){
                    if(this.board[x0+1][y0+1] == other || this.board[x0+1][y0+1] == otherKing){
                        space tempSpace = this.board[x0][y0];
                        this.board[x0][y0]= space.EMPTY;
                        this.board[x1][y1] = tempSpace;
                    }
                    else{
                        throw new InvalidMoveException("There is no enemy piece in your path");
                    }
                }
                else if(greaterx1 && !greatery1){
                    if(this.board[x0+1][y0-1] == other || this.board[x0+1][y0-1] == otherKing){
                        space tempSpace = this.board[x0][y0];
                        this.board[x0][y0]= space.EMPTY;
                        this.board[x1][y1] = tempSpace;
                    }
                    else{
                        throw new InvalidMoveException("There is no enemy piece in your path");
                    }
                }
                else if(!greaterx1 && greatery1){
                    if(this.board[x0-1][y0+1] == other || this.board[x0-1][y0+1] == otherKing){
                        space tempSpace = this.board[x0][y0];
                        this.board[x0][y0]= space.EMPTY;
                        this.board[x1][y1] = tempSpace;
                    }
                    else{
                        throw new InvalidMoveException("There is no enemy piece in your path");
                    }

                }
                else if(!greaterx1 && !greatery1){
                    if(this.board[x0-1][y0-1] == other || this.board[x0-1][y0-1] == otherKing){
                        space tempSpace = this.board[x0][y0];
                        this.board[x0][y0]= space.EMPTY;
                        this.board[x1][y1] = tempSpace;

                    }
                    else{
                        throw new InvalidMoveException("There is no enemy piece in your path");
                    }
                }

            }
        }
    }

    public static void main(String args[]) throws InvalidMoveException {
        CheckersBoard cb1 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb1.initBoard();
        cb1.printBoard();
        cb1.move(1,2, 0,3, cb1.player1, moveType.move);
        cb1.printBoard();
    }
}

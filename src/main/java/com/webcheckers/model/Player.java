package com.webcheckers.model;

public class Player{
    private String username;
    private int pieces;
    private int kings;

    /**
     * Sets up a player to play the game with 12 pieces and 0 kings
     */
    public void setupPlayer(){
        this.pieces = 12;
        this.kings = 0;
    }
    /**
     * Kings a single piece
     */
    public void kingPiece(){
        this.kings++;
        this.pieces--;
    }

    /**
     * Subtracts a single piece based on it's type
     * @param type
     */
    public void pieceLost(CheckersBoard.space type){
        if(type.equals(CheckersBoard.space.PLAYER1) || type.equals(CheckersBoard.space.PLAYER2)){
            this.pieces--;
        }
        else{
            this.kings--;
        }
    }

    public boolean equals(Player other){
        if(this)
    }
}

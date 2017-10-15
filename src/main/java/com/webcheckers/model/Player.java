package com.webcheckers.model;

public class Player {
    private int pieces;
    private int kings;

    private void setupPlayer(){
        /**
         * Sets up a player to play the game with 12 pieces and 0 kings
         */
        this.pieces = 12;
        this.kings = 0;
    }

    private void kingPiece(){
        /**
         * Kings a single piece
         */
        this.kings++;
        this.pieces--;
    }

    private void pieceLost(CheckersBoard.space type){
        /**
         * Subtracts a single piece based on it's type
         */
        if(type.equals(CheckersBoard.space.PLAYER1) || type.equals(CheckersBoard.space.PLAYER2)){
            this.pieces--;
        }
        else{
            this.kings--;
        }
    }
}

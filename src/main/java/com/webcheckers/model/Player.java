package com.webcheckers.model;

public class Player{
    private String name;

    private int pieces;
    private int kings;

    public Player(String name) {
        this.pieces = 12;
        this.kings = 0;
        this.name = name;
    }


    public String getName(){
        return name;
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
        if(this.name.equals(other.name)){
            return true;
        }
        return false;
    }
}

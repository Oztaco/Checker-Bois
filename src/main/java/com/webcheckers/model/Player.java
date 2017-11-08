package com.webcheckers.model;

import spark.Session;

import java.util.ArrayList;

public class Player{
    private String name;

    private int pieces;
    private int kings;
    private ArrayList<String> ids;
    private Session playerSession;      //TODO ADD PLAYERSESSION


    //
    //CONSTRUCTORS
    //
    public Player(String name) {
        this.pieces = 12;
        this.kings = 0;
        this.name = name;
    }

    //
    //GETTERS
    //
    public String getName(){
        return name;
    }
    public ArrayList<String> getIds() {
        return ids;
    }


    //
    //PUBLIC METHODS
    //
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


    //
    //GENERIC METHODS
    //
    public boolean equals(Player other){
        if(this.name.equals(other.name)){
            return true;
        }
        return false;
    }
}

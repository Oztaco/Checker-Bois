package com.webcheckers.model;

public enum MoveType {
    ATTACK(0),
    MOVE(1);

    private final int value;

    private MoveType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}

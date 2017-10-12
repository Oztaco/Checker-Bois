package com.webcheckers.model;

import org.omg.CORBA.INVALID_ACTIVITY;

public class CheckersBoard {

    public enum space{
        EMPTY,
        INVALID,
        PLAYER1,
        PLAYER2,
        PLAYER1KING,
        PLAYER2KING
    }
    private space[][] board;
    private Player player1;
    private Player player2;

}

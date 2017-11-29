package com.webcheckers.model;

/*======================================================================================================================
    PastMove

    Used to save a list of past moves in a game in order to easily revert moves
  ====================================================================================================================*/
public class PastMove {
    private int x0;
    private int y0;
    private int x1;
    private int y1;
    private MoveType type;
    private Player player;

/*  #######################################################################################################
    Constructor Methods
    #######################################################################################################*/
    public PastMove(int x0, int y0, int x1, int y1, MoveType type, Player player) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.type = type;
        this.player = player;
    }

/*  #######################################################################################################
    Getter Methods
    #######################################################################################################*/
    public int getX0() {
        return x0;
    }

    public int getY0() {
        return y0;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public MoveType getType() {
        return type;
    }

    public Player getPlayerPastMove() {
        return player;
    }

/*  #######################################################################################################
    Communication Methods
    #######################################################################################################*/
    public String getPastMoveAsString(){
        return "{"
                + "\"player\": \"" + this.player.getPlayerSessionId() + "\", "
                + "\"type\": " + this.type.getValue()+ ", "
                + "\"x0\": " + this.x0 + ", "
                + "\"y0\": " + this.y0 + ", "
                + "\"x\": " + this.x1 + ", "
                + "\"y\": " + this.y1 + "}";
    }
}

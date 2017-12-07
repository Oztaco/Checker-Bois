package com.webcheckers.model.gamelogic;

import java.util.ArrayList;

public class Player{
    private String name;
    private ArrayList<String> ids;
    private String playerSessionId;


    //##################################################################################################################
    //CONSTRUCTORS
    //##################################################################################################################
    public Player(String sessionID, String name) {
        this.playerSessionId = sessionID;
        this.name = name;
        this.ids = new ArrayList<>();
    }

    //##################################################################################################################
    //GETTERS
    //##################################################################################################################
    public String getName(){
        return name;
    }
    public String getPlayerSessionId(){
        return this.playerSessionId;
    }
    public ArrayList<String> getIds() {
        return this.ids;
    }

    //##################################################################################################################
    //PUBLIC METHODS
    //##################################################################################################################

    /**
     * Adds a game to the
     * @param id
     */
    public void addToGame(String id){
        this.ids.add(id);
    }



    //##################################################################################################################
    //COMMUNICATION METHODS
    //##################################################################################################################
    /**
     * getPlayerAsString()
     *
     * Gets a String representation of a player for JSON file format
     * @return
     */
    public String getPlayerAsString(){
        //{"username": "Efe Ozturko","id": "sessionID"}
        return "{\"username\": \"" + this.name + "\", \"id\": \"" + this.playerSessionId + "\"}";
    }

    //##################################################################################################################
    //GENERIC METHODS
    //##################################################################################################################
    public boolean equals(Player other){
        if(this.name.equals(other.name)){
            return true;
        }
        return false;
    }
}

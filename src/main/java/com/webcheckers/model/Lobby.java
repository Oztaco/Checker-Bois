package com.webcheckers.model;

import com.webcheckers.model.Exceptions.GameNotAddedException;
import com.webcheckers.model.Exceptions.PlayerNotAddedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Lobby {
    private final String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    private ArrayList<Player> players;
    private HashMap<String,CheckersBoard> games;


    public Lobby(){
        this.games = new HashMap<String,CheckersBoard>();
        this.players = new ArrayList<Player>();
    }

    /**
     * addPlayer(Player player)
     *
     * adds a player specified to the lobby.
     * @param player
     * @throws PlayerNotAddedException
     */
    public void addPlayer(Player player) throws PlayerNotAddedException {
        try{
            this.players.add(player);
        }
        catch(Exception e){
            throw new PlayerNotAddedException("Player was not added to the Lobby");
        }
    }

    /**
     * addGame(CheckersBoard game)
     *
     * Adds game to list of games, given it's players are in the lobby.
     * @param game
     * @throws GameNotAddedException
     */
    public void addNewGame(CheckersBoard game) throws GameNotAddedException{
        Player player1 = game.getPlayer(1);
        Player player2 = game.getPlayer(2);

        if(this.players.contains(player1) && this.players.contains(player2)){
            this.games.put(game.getId(),game);
        }
        else{
            throw new GameNotAddedException("The players trying to play are not in the lobby yet");
        }
    }

    /**
     * Generates a Random ID to assign to every game.
     * @return Int randID
     */
    public String generateID(){
        int range = this.validChars.length()-1;
        String randId = "";
        for(int i = 0; i <= 6; i++){
            int index = (int)(Math.random()*range);
            randId = randId + validChars.charAt(index);
        }

        if(games.containsKey(randId)){
            randId = generateID();    //Make a new ID if the ID generated is already in use
        }

        return randId;
    }

    /**
     * Prints lobby for testing purposes
     */
    public void printLobby(){
        System.out.println("Lobby:");
        String players = "";
        for(int i = 0;i < this.players.size();i++){
            players += (this.players.get(i).getName() + ", " );
        }
        System.out.println("\tUnique players: " + players);
        System.out.println("\tNumber of unique games: " + this.games.size());
    }

    /**
     * //TODO WRITE THIS WHOLE THING LOL
     * @return
     */
    public String getPlayersJson(){

        return null;
    }

    /**
     * //TODO WRITE THIS TOO LOL
     * Getter for games
     * @return ArrayList<CheckersBoard>
     */
    public ArrayList<CheckersBoard> getGamesJson(){
        //Jon owes me $5 for wings

        return null;
    }

    public static void main(String args[]){

    }
}

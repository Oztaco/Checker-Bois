package com.webcheckers.model;

import com.webcheckers.model.Exceptions.GameNotAddedException;
import com.webcheckers.model.Exceptions.PlayerNotAddedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Lobby {
    private final String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    private HashMap<String,Player> players;     //Maps Usernames to Player Objects
    private HashMap<String,Game> games;         //Maps IDs to Game Objects

    //##################################################################################################################
    //Constructor
    //##################################################################################################################
    public Lobby(){
        this.games = new HashMap<String,Game>();
        this.players = new HashMap<String,Player>();
    }


    //##################################################################################################################
    //Getters
    //##################################################################################################################
    public Game getGame(String id){
        return this.games.get(id);
    }


    //##################################################################################################################
    //Lobby Management Methods
    //##################################################################################################################
    /**
     * -----------------------------------------------------------------------------------------------------------------
     * addPlayer(Player player)
     *
     * adds a player specified to the lobby.
     * @param username
     * @throws PlayerNotAddedException
     * -----------------------------------------------------------------------------------------------------------------
     */
    public void addPlayer(String username) throws PlayerNotAddedException {
        try{
            Player newPlayer = new Player(username);
            this.players.put(username,newPlayer);
        }
        catch(Exception e){
            throw new PlayerNotAddedException("Player was not added to the Lobby");
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * addGame(CheckersBoard game)
     *
     * Adds game to list of games, given it's players are in the lobby.
     * @param p1, p2
     * @throws GameNotAddedException
     * -----------------------------------------------------------------------------------------------------------------
     */
    public void addNewGame(String p1, String p2) throws GameNotAddedException{
        String newId = generateID();

        if(this.players.containsKey(p1) && this.players.containsKey(p1)){
            Game game = new Game(this.players.get(p1),this.players.get(p2),newId);
            this.games.put(newId,game);
        }
        else{
            throw new GameNotAddedException("The players trying to play are not in the lobby yet");
        }
    }


    //##################################################################################################################
    //Private Methods
    //##################################################################################################################
    /**
     * -----------------------------------------------------------------------------------------------------------------
     * generateID()
     *
     * Generates a Random ID to assign to every game.
     * @return randID
     * -----------------------------------------------------------------------------------------------------------------
     */
    private String generateID(){
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


    //##################################################################################################################
    //PUBLIC METHODS
    //##################################################################################################################



    //##################################################################################################################
    //TESTING METHODS
    //##################################################################################################################
    /**
     * -----------------------------------------------------------------------------------------------------------------
     * printLobby()
     *
     * Prints lobby for testing purposes
     * -----------------------------------------------------------------------------------------------------------------
     */
    public void printLobby(){
        System.out.println("Lobby:");
        String players = "";
        for(int i = 0;i < this.players.size();i++){
            players += (this.players.get(i).getName() + ", " );
        }
        System.out.println("\tUnique players: " + players);
        System.out.println("\tNumber of unique games: " + this.games.keySet().size());
    }


    //##################################################################################################################
    //COMMUNICATION METHODS
    //##################################################################################################################
    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getPlayersAsString()
     *
     * Formats the players in the lobby for use in JSON files
     * TODO: METHOD STUBBED
     * @return
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getPlayersAsString(){
        return null;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getGamesAsString()
     *
     * Formats all games in a Lobby for use in JSON files
     * TODO : METHOD STUBBED
     * @return ArrayList<CheckersBoard>
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getGamesAsString(){
        return null;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getGamesAsPlayer
     *
     * Formats all games for a specific player as a string for use in a JSON file
     * @param player
     * @return playerGames
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getGamesAsString(String player){
        String playerGames = "";
        for(String id : this.players.get(player).getIds()){
            playerGames.concat("{" + this.getGame(id).getSimpleGameAsString() + "},");
        }
        if(playerGames.length() > 0){
            playerGames.substring(0,playerGames.length()-1);
        }
        return playerGames;
    }


    //##################################################################################################################
    //MAIN TESTING
    //##################################################################################################################

    public static void main(String args[]){
        Lobby l = new Lobby();
        try {
            l.addPlayer("peepee");
        }
        catch(PlayerNotAddedException e){
            System.out.println(e.getMessage());
        }

    }

}

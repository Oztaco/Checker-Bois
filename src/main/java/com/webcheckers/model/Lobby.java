package com.webcheckers.model;

import com.webcheckers.model.Exceptions.GameNotAddedException;
import com.webcheckers.model.Exceptions.PlayerNotAddedException;

import java.util.HashMap;
import java.util.Random;

public class Lobby {
    private final String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    private HashMap<String,Player> players;     //Maps Session ID's to Player Objects
    private HashMap<String,Game> games;         //Maps IDs to Game Objects

/*  #######################################################################################################
    Private Methods
    #######################################################################################################*/
    public Lobby(){
        this.games = new HashMap<>();
        this.players = new HashMap<>();
    }


/*  #######################################################################################################
    Getter Methods
    #######################################################################################################*/
    public Game getGame(String id){
        return this.games.get(id);
    }
    public Player getPlayerBySessionID(String sessionID) {
        return players.get(sessionID);
    }
    private Player getPlayer(String username){
        try{
            return this.players.get(username);
        } catch(Exception e){
            return null;
        }

    }

/*  #######################################################################################################
    Lobby Management Methods
    #######################################################################################################*/
    /**
     * ----------------------------------------------------------------------------------------------------
     * addPlayer(Player player)
     *
     * adds a player specified to the lobby.
     * @param username
     * @throws PlayerNotAddedException
     * ----------------------------------------------------------------------------------------------------
     */
    public void addPlayer(String sessionID, String username) {
        Player newPlayer = new Player(sessionID, username);
        this.players.put(sessionID, newPlayer);
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     * addGame(CheckersBoard game)
     *
     * Adds game to list of games, given it's players are in the lobby.
     * @param p1, p2
     * @throws GameNotAddedException
     * ----------------------------------------------------------------------------------------------------
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

/*  #######################################################################################################
    Private Methods
    #######################################################################################################*/
    /**
     * ----------------------------------------------------------------------------------------------------
     * generateID()
     *
     * Generates a Random ID to assign to every game.
     * @return randID
     * ----------------------------------------------------------------------------------------------------
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


/*  #######################################################################################################
    Public Methods
    #######################################################################################################*/




/*  #######################################################################################################
    Testing Methods
    #######################################################################################################*/
    /**
     * ----------------------------------------------------------------------------------------------------
     * printLobby()
     *
     * Prints lobby for testing purposes
     * ----------------------------------------------------------------------------------------------------
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


/*  #######################################################################################################
    Communication Methods
    #######################################################################################################*/
    /**
     * ----------------------------------------------------------------------------------------------------
     * getPlayersAsString()
     *
     * Formats the players in the lobby for use in JSON files
     * TODO: METHOD STUBBED
     * @return
     * ----------------------------------------------------------------------------------------------------
     */
    public String getPlayersAsString(){
        String playersAsString = "";
        for(String current : this.players.keySet()){
            
        }

        return playersAsString;
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     * getGamesAsString()
     *
     * Formats all games in a Lobby excluding a player for use in JSON files
     * TODO : METHOD STUBBED
     * @return ArrayList<CheckersBoard>
     * ----------------------------------------------------------------------------------------------------
     */
    public String getGamesAsString(String playerID){
        return null;
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     * getGamesAsStringForPlayer
     *
     * Formats all games for a specific player as a string for use in a JSON file
     * @param player
     * @return playerGames
     * ----------------------------------------------------------------------------------------------------
     */
    public String getGamesAsStringForPlayer(String player){
        String playerGames = "";
        if(this.players.get(player).getIds().size() > 0){
            for(String id : this.players.get(player).getIds()){
                playerGames.concat("{" + this.getGame(id).getSimpleGameAsString() + "},");
            }
            if(playerGames.length() > 0){
                playerGames.substring(0,playerGames.length()-1);
            }
        }
        return playerGames;
    }


/*  #######################################################################################################
    Main Testing
    #######################################################################################################*/

    public static void main(String args[]){
        Lobby l = new Lobby();
        l.addPlayer("1","peepee");
        l.addPlayer("2", "Gerard");
        
        System.out.println("JSON OF GAMES FOR PEEPEE WILL BE FORMATTED AS:\n\n\t" + l.getGamesAsString("peepee"));
    }
}

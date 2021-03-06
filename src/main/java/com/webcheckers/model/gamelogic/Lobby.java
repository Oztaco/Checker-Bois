package com.webcheckers.model.gamelogic;

import com.webcheckers.Exceptions.GameNotAddedException;
import com.webcheckers.Exceptions.InvalidMoveException;
import com.webcheckers.Exceptions.PlayerNotAddedException;

import java.util.HashMap;
import java.util.logging.Logger;

public class Lobby {
    private static final Logger LOG = Logger.getLogger(Lobby.class.getName());

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

    public int getPlayer1Pieces(String gameID){
        return this.getGame(gameID).getPlayer1Pieces();
    }

    public int getPlayer2Pieces(String gameID){
        return this.getGame(gameID).getPlayer2Pieces();
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
     * removePlayer
     *
     * Removes a player from the lobby based on a sessionID
     * @param sessionID
     * ----------------------------------------------------------------------------------------------------
     */
    public void removePlayer(String sessionID){
        this.players.remove(sessionID);
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     * addGame(CheckersBoard game)
     *
     * Adds game to list of games, given it's players are in the lobby.
     * @param sessionID1
     * @param sessionID2
     * @throws GameNotAddedException
     * ----------------------------------------------------------------------------------------------------
     */
    public String addNewGame(String sessionID1, String sessionID2){
        LOG.severe("Lobby -> addNewGame invoked");
        LOG.severe("SessionID1: " + sessionID1);
        LOG.severe("SessionID2: " + sessionID2);
        String newId = generateID();
        Game game = new Game(this.players.get(sessionID1),this.players.get(sessionID2),newId);
        this.games.put(newId,game);
        this.players.get(sessionID1).addToGame(newId);
        this.players.get(sessionID2).addToGame(newId);
        return newId;
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

    public void makeMove(String gameID, int x0, int x1, int y0, int y1, MoveType m) throws InvalidMoveException{
        Player currPlayer = this.games.get(gameID).getPlayerTurn();
        this.games.get(gameID).playTurn(currPlayer, x0,y0,x1,y1,m);
    }

    public void playerResigned(String gameID, String playerSessionID) {
        LOG.severe("Player ID {" + playerSessionID + "} resigned in game {" + gameID + "}");
        Player p = this.players.get(playerSessionID);
        LOG.severe("Name= " + p.getName());
        this.games.get(gameID).playerResigned(p);
    }


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
            playersAsString += (this.players.get(current).getPlayerAsString() + ", ");
        }
        if(playersAsString.length() > 1){
            playersAsString = playersAsString.substring(0,playersAsString.length()-2);
        }
        return playersAsString;
    }

    public String getGameAsString(String gameID, String sessionID){
        if (!this.games.containsKey(gameID)) {
            String errorMsg =
                "Game {" + gameID + "} not found in the lobby. Can not complete getGameAsString request.";
            LOG.severe(errorMsg);
            return errorMsg;
        }
        return this.games.get(gameID).getGameAsString(this.players.get(sessionID));
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
        String notPlayerGames = "";

        if(this.games.size() > 1) {
            for (String game : this.games.keySet()) {
                if (!this.players.get(playerID).getIds().contains(game)) {
                    notPlayerGames += (this.games.get(game).getSimpleGameAsString() + ", ");
                }
            }
        }
        if(notPlayerGames.length() >= 1){
            notPlayerGames = notPlayerGames.substring(0,notPlayerGames.length()-2);
        }

        return notPlayerGames;
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
        if (!this.players.containsKey(player)) {
            String errorMsg =
                "Player {" + player + "} not found in the lobby. Can not complete getGamesAsStringForPlayer request.";
            LOG.severe(errorMsg);
            return errorMsg;
        }
        String playerGames = "";
        if(this.players.get(player).getIds().size() > 0){
            for(String id : this.players.get(player).getIds()){     //For each game the player is currently enrolled in
                playerGames += (this.getGame(id).getSimpleGameAsString() + ", ");
            }
            if(playerGames.length() > 1){
                playerGames = playerGames.substring(0,playerGames.length()-2);
            }
        }
        return playerGames;
    }


    public String getGameBoardAsString(String id, String playerSessionID){
        Player theOneWhoKnocks = this.players.get(playerSessionID);
        return this.games.get(id).getGameBoardAsString(theOneWhoKnocks);
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     * getNumOfPlayers
     *
     * Gets the number of Players currently signed in
     * @return numPlayers
     * ----------------------------------------------------------------------------------------------------
     */
    public int getNumOfPlayers() { return this.players.size(); }

/*  #######################################################################################################
    Main Testing
    #######################################################################################################*/

    public static void main(String args[]){
        Lobby l = new Lobby();
        l.addPlayer("1","peepee");
        l.addPlayer("2", "Gerard");
        l.addPlayer("3", "Nerd");
        l.addNewGame("1","2");

        //peepee has one game with his good friend gerard
        System.out.println("All Players\n\t" + l.getPlayersAsString());
        System.out.println("Games for \"peepee\"\n\t" + l.getGamesAsStringForPlayer("1"));
        System.out.println("Games \"peepee\" was not invited to\n\t" + l.getGamesAsString("1"));

        l.addNewGame("1","3");

        //peepee gets a second game with his sworn enemy nerd
        System.out.println("All Players\n\t" + l.getPlayersAsString());
        System.out.println("Games for \"peepee\"\n\t" + l.getGamesAsStringForPlayer("1"));
        System.out.println("Games \"peepee\" was not invited to\n\t" + l.getGamesAsString("1"));

        l.addNewGame("2", "3");

        //Woe is me!  Gerard and nerd are now in cahoots and playing a game WITHOUT PEEPEE
        System.out.println("All Players\n\t" + l.getPlayersAsString());
        System.out.println("Games for \"peepee\"\n\t" + l.getGamesAsStringForPlayer("1"));
        System.out.println("Games \"peepee\" was not invited to\n\t" + l.getGamesAsString("1"));

    }
}

package com.webcheckers.appl;

import com.webcheckers.Exceptions.InvalidMoveException;

import java.util.logging.Logger;

import com.webcheckers.model.gamelogic.Lobby;
import com.webcheckers.model.gamelogic.MoveType;

/**
 * The class that centralizes communication within the server,
 * glueing the model and the UI together and maintaining state.
 * This class should coordinate everything, like the signing in
 * and management stuff.
 *
 * //TODO FIGURE OUT WHAT I COMMENTED OUT I DON'T GET IT
 *
 * @author <a href='mailto:exo3392@rit.edu'>Efe Ozturkoglu</a>
 */

public class GameCenter {
    private Lobby lobby;
    //private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());
    //ArrayList<PlayerSession> playerSessions = new ArrayList<PlayerSession>();


/*  #######################################################################################################
    Constructor
    #######################################################################################################*/
    public GameCenter() {
         this.lobby = new Lobby();
     }


/*  #######################################################################################################
    Adders
    #######################################################################################################*/
    /**
     * -----------------------------------------------------------------------------------------------------------------
     * addPlayer(String username)
     *
     * Routes an Add Player request from the UI to the corresponding Model method
     * TODO : FORMAT EXCEPTION
     * @param username
     * -----------------------------------------------------------------------------------------------------------------
     */
    public void addPlayer(String sessionID, String username) {
        this.lobby.addPlayer(sessionID, username);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * addNewGame(String p1, String p2)
     *
     * Routes an Add Game request from the UI to the corresponding Model Method
     * @param p1
     * @param p2
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String addNewGame(String p1, String p2){
        return this.lobby.addNewGame(p1,p2);
    }

/*  #######################################################################################################
    Private Methods
    #######################################################################################################*/

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getPlayers
     *
     * Routes a request from the UI to get a JSON string of the Players list to the proper aspect of the Lobby
     * @return allPlayers
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getPlayers(){
        return lobby.getPlayersAsString();
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getAllGames
     *
     * Routes a request from the UI to get a JSON string of the Map of Games to the proper aspect of the lobby excluding
     * the one's that a player's in
     * @return allGames
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getAllGames(String sessionID){
        return lobby.getGamesAsString(sessionID);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getAllGamesForPlayer
     *
     * Routes a request from the UI to get a string representation of the Games for a single player for use in a JSON
     * file to the proper aspect of the lobby
     * @param sessionID
     * @return gamesForPlayer
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getAllGamesForPlayer(String sessionID){
        return lobby.getGamesAsStringForPlayer(sessionID);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getGame
     *
     * Routes a request from the UI to get a JSON string of a Game to the proper aspect of the lobby
     * @param gameId
     * @param playerSessionID
     * @return game
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getGame(String gameId, String playerSessionID){
        return lobby.getGameAsString(gameId, playerSessionID);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * removePlayer
     *
     * Removes the player with the specified ID from the lobby
     * @param sessionID
     * -----------------------------------------------------------------------------------------------------------------
     */
    public void removePlayer(String sessionID){
        this.lobby.removePlayer(sessionID);
    }

    /**
     * makeMove
     *
     * makes a Move based on the given information
     */
    public void makeMove(String gameID, int x0, int x1, int y0, int y1, MoveType m) throws InvalidMoveException{
        this.lobby.makeMove(gameID, x0,y0,x1,y1,m);
    }

    public void playerResigned(String gameID, String sessionID){
        this.lobby.playerResigned(gameID, sessionID);
    }

    public int getNumOfPlayers() { return lobby.getNumOfPlayers(); }


    //##################################################################################################################
    // Public Methods
    //##################################################################################################################
    public Lobby getLobby() {
        Logger LOG = Logger.getLogger("getLobby called: ");

        LOG.severe("lobby=" + this.lobby);
        return this.lobby;
    }

    public int getPlayer1Pieces(String gameID){
        return this.lobby.getPlayer1Pieces(gameID);
    }

    public int getPlayer2Pieces(String gameID){
        return this.lobby.getPlayer2Pieces(gameID);
    }
}

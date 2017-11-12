package com.webcheckers.appl;

import com.webcheckers.model.Exceptions.GameNotAddedException;
import com.webcheckers.model.Exceptions.PlayerNotAddedException;
import com.webcheckers.model.Lobby;

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


    //##################################################################################################################
    // Constructors
    //##################################################################################################################
     public GameCenter() {
         this.lobby = new Lobby();
     }


    //##################################################################################################################
    // Routing methods
    //##################################################################################################################
    /**
     * -----------------------------------------------------------------------------------------------------------------
     * addPlayer(String username)
     *
     * Routes an Add Player request from the UI to the corresponding Model method
     * TODO : FORMAT EXCEPTION
     * @param username
     * -----------------------------------------------------------------------------------------------------------------
     */
    public void addPlayer(String username) {
        try {
            this.lobby.addPlayer(username);
        } catch (PlayerNotAddedException e) {

        }
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
    public void addNewGame(String p1, String p2){
        try{
            this.lobby.addNewGame(p1,p2);
        }
        catch(GameNotAddedException e){
            //TODO WHAT DO WE DO
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getPlayers()
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
     * Routes a request from the UI to get a JSON string of the Map of Games to the proper aspect of the lobby
     * @return allGames
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getAllGames(){
        return lobby.getGamesAsString();
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getAllGamesForPlayer
     *
     * Routes a request from the UI to get a string representation of the Games for a single player for use in a JSON
     * file to the proper aspect of the lobby
     * @param username
     * @return gamesForPlayer
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getAllGamesForPlayer(String username){
        return lobby.getGamesAsString(username);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * getGame
     *
     * Routes a request from the UI to get a JSON string of a Game to the proper aspect of the lobby
     * @param id
     * @param player
     * @return game
     * -----------------------------------------------------------------------------------------------------------------
     */
    public String getGame(String id, int player){
        return lobby.getGame(id).getGameBoardJSON(player);
    }
}

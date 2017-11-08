package com.webcheckers.appl;

import java.util.logging.Logger;

import java.util.ArrayList;
import com.webcheckers.appl.PlayerSession;
import com.webcheckers.model.Exceptions.GameNotAddedException;
import com.webcheckers.model.Exceptions.PlayerNotAddedException;
import com.webcheckers.model.Lobby;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;
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


    //
    // Constructors
    //
     public GameCenter() {
         this.lobby = new Lobby();
     }


    //
    // Public methods
    //

    /**
     * Routes an Add Player request from the UI to the corresponding Model method
     * @param username
     */
    public void addPlayer(String username){
         try {
             this.lobby.addPlayer(username);
         }
         catch(PlayerNotAddedException e){
             //TODO WHAT DO WE DO
         }
    }

    /**
     * Routes an Add Game request from the UI to the corresponding Model Method
     * @param p1
     * @param p2
     */
    public void addGame(String p1, String p2){
        try{
            this.lobby.addNewGame(p1,p2);
        }
        catch(GameNotAddedException e){
            //TODO WHAT DO WE DO
        }
    }

    /**
     public void addSession(String _username, String _ip) {
     playerSessions.add(new PlayerSession(_username, _ip));
     }
     */

    /**
    public PlayerSession getSessionByIp(String _ip) {
        for (PlayerSession p : playerSessions) {
            if (p.getIp() == _ip) {
                return p;
            }
        }
        return null; //I added this because I needed to compile my code //TODO
    }
     */
}

package com.webcheckers.appl;

import java.util.logging.Logger;

import Java.util.ArrayList;
import com.webcheckers.appl.PlayerSession;


/**
 * The class that centralizes communication within the server,
 * glueing the model and the UI together and maintaining state.
 *
 * @author <a href='mailto:exo3392@rit.edu'>Efe Ozturkoglu</a>
 */
public class GameCenter {
  private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());

  //
  // Constants
  //



  //
  // Attributes
  //

  ArrayList<PlayerSession> playerSessions = new ArrayList<PlayerSession>();

  //
  // Constructors
  //

  public GameCenter() {

  }

  //
  // Public methods
  //

  public void addSession(String _username, String _ip) {
    playerSessions.add(new PlayerSession(_username, _ip));
  }
  
  public PlayerSession getSessionByIp(String _ip) {
    for (PlayerSession p : playerSessions) {
      if (p.getIp() == _ip) {
        return p;
      }
    }
  }

}

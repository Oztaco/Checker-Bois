package com.webcheckers.appl;

import java.util.logging.Logger;

import java.util.ArrayList;
import com.webcheckers.appl.PlayerSession;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;


/**
 * The class that centralizes communication within the server,
 * glueing the model and the UI together and maintaining state.
 * This class should coordinate everything, like the signing in
 * and management stuff.
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
    return null; //I added this because I needed to compile my code //TODO
  }

}

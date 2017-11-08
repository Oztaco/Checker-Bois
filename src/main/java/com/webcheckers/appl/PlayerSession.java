package com.webcheckers.appl;

import java.util.logging.Logger;


/**
 * This class stores the player session data
 *
 * @author <a href='mailto:exo3392@rit.edu'>Efe Ozturkoglu</a>
 */
public class PlayerSession {

  private String username;
  private String ip;

  //
  // Constructors
  //
  public PlayerSession(String _username, String _ip) {
    username = _username;
    ip = _ip;
  }

  //
  // Public methods
  //

  public String getUsername() {
    return username;
  }
  
  public String getIp() {
    return ip;
  }

}

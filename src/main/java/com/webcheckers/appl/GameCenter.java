package com.webcheckers.appl;

import java.util.logging.Logger;

import com.example.model.GuessGame;

/**
 * The class that centralizes communication within the server,
 * glueing the model and the UI together and maintaining state.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Efe Ozturkoglu</a>
 */
public class GameCenter {
  private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());

  //
  // Constants
  //



  //
  // Attributes
  //

  //
  // Constructors
  //

  //
  // Public methods
  //

  /**
   * Get a new {@Linkplain PlayerServices} object to provide client-specific services to
   * the client who just connected to this application.
   *
   * @return
   *   A new {@Link PlayerServices}
   */
  public PlayerServices newPlayerServices() {
    LOG.fine("New player services instance created.");
    return new PlayerServices(this);
  }

  /**
   * Create a new {@Linkplain GuessGame} game.
   *
   * @return
   *   A new {@link GuessGame}
   */
  public GuessGame getGame() {
    return new GuessGame(this);
  }

  /**
   * Collect sitewide statistics when a game is finished.
   */
  public void gameFinished() {
    // do some application-wide book-keeping
    synchronized (this) {  // protect the critical code
      totalGames++;
    }
  }

  /**
   * Get a user message about the sitewide statistics.
   *
   * @return
   *   The message to the user about global game statistics.
   */
  public synchronized String getGameStatsMessage() {
    if (totalGames > 1) {
      return String.format(GAMES_PLAYED_FORMAT, totalGames);
    } else if (totalGames == 1) {
      return ONE_GAME_MESSAGE;
    } else {
      return NO_GAMES_MESSAGE;
    }
  }
}

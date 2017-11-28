package com.webcheckers.ui;

import static spark.Spark.halt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import com.webcheckers.appl.GameCenter;

/**
 * The UI Controller to expose the functionality that starts a brand new game
 * against a given player. The client that requests this route should pass in
 * a HTTP parameter that is the Player ID that it wants to play with. The route
 * should respond back with a Game ID of the newly created game.
 *
 * @author <a href='mailto:exo3392@rit.edu'>Efe Ozturkoglu</a>
 */
public class PostCreateBoardRoute implements Route {
  private static final Logger LOG = Logger.getLogger( PostCreateBoardRoute.class.getName());

  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;

  /**
   * Create the Spark Route (UI controller) for the
   * {@code POST /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public  PostCreateBoardRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    //
    this.templateEngine = templateEngine;
    this.gameCenter = gameCenter;
    //
    LOG.config("PostCreateBoardRoute is initialized.");
  }

  /**
   * Handle creating a new game. The 'request' should contain the 'opponent_player_id'
   * parameter, and the response should simply be the Game ID of the new game that
   * was created in the gameCenter.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Sign In page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer(" PostCreateBoardRoute is invoked.");
    LOG.severe(request.queryParams("username")); // NOTE TO CAM: this is how you
                                                  // access parameters for 'opponent_player_id'
    // Remove the line below. It's just an example of how to
    // access gameCenter from here

    //THE FOLLOWING VARIABLES ARE PLACEHOLDERS FOR THE ID'S OF PLAYERS TRYING TO PLAY
    String p1 = ""; //PLAYER1ID
    String p2 = ""; //PLAYER2ID
    String gameID = gameCenter.addNewGame(p1,p2);
    gameCenter.getAllGames(gameID);
    return null;
  }

}
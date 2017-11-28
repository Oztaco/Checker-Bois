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
import com.webcheckers.model.Lobby;
import com.webcheckers.model.Player;

/**
 * The API route to get a JSON representation of the entire lobby
 * which includes:
 *  1. List of all players
 *  2. List of the games the current player is in
 *  3. List of all *other* games being played
 *
 * @author <a href='mailto:exo3392@rit.edu'>Efe Ozturkoglu</a>
 */
public class GetLobbyRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetLobbyRoute.class.getName());

  static final String TITLE_ATTR = "title";
  static final String PLAYER_GAMES_ATTR = "currentPlayerGames";
  static final String ALL_GAMES_ATTR = "allGames";
  static final String ALL_PLAYERS_ATTR = "allPlayers";

  static final String LAST_UPDATED_ALL_PLAYERS_ATTR = "lastUpdated_allPlayers";
  static final String LAST_UPDATED_ALL_GAMES_ATTR = "lastUpdated_allGames";
  static final String LAST_UPDATED_CURRENT_GAMES_ATTR = "lastUpdated_currentGames";

  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;

  /**
   * Create the Spark Route (UI controller) for the
   * {@code GET /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetLobbyRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    //
    this.templateEngine = templateEngine;
    this.gameCenter = gameCenter;
    //
    LOG.config("GetLobbyRoute is initialized.");
  }

  /**
   * Render the JSON response to get the whole lobby
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetLobbyRoute is invoked.");
    //
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");
    String sessionID = request.session().id();

    String allPlayers = this.gameCenter.getPlayers();
    String playerGames = this.gameCenter.getAllGamesForPlayer(sessionID);
    String otherGames = this.gameCenter.getAllGames(sessionID);

    // TO DO: update timestamp to be based on when the board was actually
    // last updated
    long timestamp = (long) (System.currentTimeMillis() / 1000L);
    vm.put(LAST_UPDATED_ALL_PLAYERS_ATTR, "" + timestamp);
    vm.put(LAST_UPDATED_ALL_GAMES_ATTR, "" + timestamp);
    vm.put(LAST_UPDATED_CURRENT_GAMES_ATTR, "" + timestamp);

    vm.put(ALL_PLAYERS_ATTR, allPlayers);
    vm.put(PLAYER_GAMES_ATTR, playerGames);
    vm.put(ALL_GAMES_ATTR, otherGames);

    return templateEngine.render(new ModelAndView(vm , "api/getLobby.ftl"));
  }

}
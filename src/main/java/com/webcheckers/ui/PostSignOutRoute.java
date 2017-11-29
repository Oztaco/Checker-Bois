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
 * The UI Controller to expose the sign-in functionality
 * and redirect to the game page.
 *
 * @author <a href='mailto:exo3392@rit.edu'>Efe Ozturkoglu</a>
 */
public class  PostSignOutRoute implements Route {
  private static final Logger LOG = Logger.getLogger( PostSignOutRoute.class.getName());

  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;

  /**
   * Create the Spark Route (UI controller) for the
   * {@code POST /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public  PostSignOutRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    //
    this.templateEngine = templateEngine;
    this.gameCenter = gameCenter;
    //
    LOG.config("PostSignOutRoute is initialized.");
  }

  /**
   * Sign out the player
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer(" PostSignOutRoute is invoked.");
    LOG.severe(request.queryParams("username"));
    gameCenter.removePlayer(request.session().id());
    
    response.redirect("/");
    return "";
  }

}
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
public class  PostSignInRoute implements Route {
  private static final Logger LOG = Logger.getLogger( PostSignInRoute.class.getName());

  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;

  /**
   * Create the Spark Route (UI controller) for the
   * {@code POST /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public  PostSignInRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    //
    this.templateEngine = templateEngine;
    this.gameCenter = gameCenter;
    //
    LOG.config("PostSignInRoute is initialized.");
  }

  /**
   * Render the WebCheckers Sign In page.
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
    LOG.finer(" PostSignInRoute is invoked.");
    LOG.severe(request.queryParams("username"));

    if (request.queryParams("username").contains("\"")) {
      //response.redirect("/signin");

      Map<String, Object> vm = new HashMap<>();
      vm.put("title", "Welcome!");
      vm.put("error", true);
      vm.put("error_message", "Usernames cannot contain \"");
      return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }

    else {
      gameCenter.addPlayer(request.session().id(), request.queryParams("username"));

      response.redirect("/game");
      return null;
    }
  }

}
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

/**
 * The UI Controller to GET the Sign in page.
 *
 * @author <a href='mailto:exo3392@rit.edu'>Efe Ozturkoglu</a>
 */
public class  GetSignInRoute implements Route {
  private static final Logger LOG = Logger.getLogger( GetSignInRoute.class.getName());

  private final TemplateEngine templateEngine;

  /**
   * Create the Spark Route (UI controller) for the
   * {@code GET /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public  GetSignInRoute(final TemplateEngine templateEngine) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    //
    this.templateEngine = templateEngine;
    //
    LOG.config(" GetSignInRoute is initialized.");
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
    LOG.finer(" GetSignInRoute is invoked.");
    //
    Map<String, Object> vm = new HashMap<>();
    return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
  }

}
package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Exceptions.InvalidMoveException;
import com.webcheckers.model.MoveType;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to expose the functionality that makes a move.  The client
 * that requests this route should pass in an HTTP parameter that is the
 * data of the move the player wishes to make (X0, Y0, X1, Y1, and MoveType). The route
 * should respond back with the new game information.
 *
 * @author <a href='mailto:ceh7606@rit.edu'>Cameron Hudson</a>
 */

public class PostResignRoute implements Route{
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
    public  PostResignRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        //
        LOG.config("PostResignRoute is initialized.");
    }

    /**
     * Handle resigning a game
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
        LOG.finer(" PostResignRoute is invoked.");
        //LOG.severe("!opponent id:" + request.headers("opponent_player_id"));

        String gameID = request.headers("gameID");
        String resigningPlayer = request.session().id();

        LOG.severe("Player " + request.session().id() + "resigned. RIP");

        gameCenter.playerResigned(gameID, resigningPlayer);

        return "";
    }

}

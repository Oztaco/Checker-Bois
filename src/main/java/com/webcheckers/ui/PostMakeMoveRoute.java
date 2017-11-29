package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
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

public class PostMakeMoveRoute implements Route{
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
    public  PostMakeMoveRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        //
        LOG.config("PostMakeMoveRoute is initialized.");
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
        LOG.finer(" PostMakeMoveRoute is invoked.");
        //LOG.severe("!opponent id:" + request.headers("opponent_player_id"));

        String gameID = request.headers("gameID");
        int x0 = Integer.valueOf(request.headers("x0"));
        int x1 = Integer.valueOf(request.headers("x1"));
        int y0 = Integer.valueOf(request.headers("y0"));
        int y1 = Integer.valueOf(request.headers("y1"));
        MoveType m = MoveType.values()[Integer.valueOf(request.headers("moveType"))];

        LOG.severe("!!!!!!!!!!!!!!!!!!!! YO GOOBER MADE A MOVE ===========================");
        LOG.severe("Movetype: " + request.headers("moveType"));
        LOG.severe("gameID  : " + request.headers("gameID"));
        LOG.severe("x0      : " + request.headers("x0"));
        LOG.severe("y0      : " + request.headers("y0"));
        LOG.severe("x1      : " + request.headers("x1"));
        LOG.severe("y1      : " + request.headers("y1"));
        gameCenter.makeMove(gameID, x0,y0,x1,y1,m);
        return "";
    }

}

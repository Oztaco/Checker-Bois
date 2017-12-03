package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Cameron Hudson on 12/3/2017.
 */
public class PostRevertMoveRoute implements Route {
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
    public  PostRevertMoveRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        //
        LOG.config("PostRevertMoveRoute is initialized.");
    }

    /**
     * Handle Reverting a move
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
        LOG.finer(" PostRevertMoveRoute is invoked.");
        //LOG.severe("!opponent id:" + request.headers("opponent_player_id"));

        String gameID = request.headers("gameID");

        LOG.severe("Game " + request.session().id() + "chose to revert last move.");

        gameCenter.getLobby().getGame(gameID).revertLastMove();

        return "";
    }
}

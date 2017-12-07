package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import freemarker.template.Template;
import spark.Route;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;
import java.util.logging.Logger;

import com.webcheckers.appl.GameCenter;

import com.webcheckers.model.gamelogic.Lobby;
import com.webcheckers.model.gamelogic.Player;

public class GetGamePageRoute implements Route {
    private static final Logger LOG = Logger.getLogger( GetGamePageRoute.class.getName());

    static final String VIEW_NAME = "game.ftl";
    static final String VIEWMODE_ATTR = "viewMode";
    static final String TITLE = "Web Checkers";
    static final String USERNAME_ATTR = "username";
    static final String ID_ATTR = "id";

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    GetGamePageRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }

    public String handle(Request request, Response response) {
        final Session httpSession = request.session();
        // final PlayerServices playerServices = httpSession.attribute(GetHomeRoute.PLAYERSERVICES_KEY);
        // GuessGame game = playerServices.currentGame();

        // com.webcheckers.model.gamelogic.Player ploop = new com.webcheckers.model.gamelogic.Player("blub");
        // com.webcheckers.model.gamelogic.Player red = new com.webcheckers.model.gamelogic.Player("red");
        // com.webcheckers.model.gamelogic.Player blue = new com.webcheckers.model.gamelogic.Player("blue");
        // com.webcheckers.model.gamelogic.CheckersBoard board = new com.webcheckers.model.gamelogic.CheckersBoard(red, blue);

        final Map<String, Object> vm = new HashMap<>();
        vm.put(VIEWMODE_ATTR, "Unknown");

        LOG.severe("=================================================================!!");
        LOG.severe("DEBUG: game center:" + gameCenter);
        LOG.severe("Lobby");
        Lobby lobby = gameCenter.getLobby();
        LOG.severe("Lobby = " + lobby);
        LOG.severe("currentPlayer");
        Player currentPlayer = lobby.getPlayerBySessionID(request.session().id());

        if (currentPlayer == null) {
            LOG.fine("Player not logged in. Redirect to signin");
            response.redirect("/");
        }
        else {
            LOG.severe("currentPlayer= " + currentPlayer); 
            LOG.severe("username");
            String username = currentPlayer.getName();
            LOG.severe("username= " + username);         
            vm.put(USERNAME_ATTR, username);
            vm.put(ID_ATTR, request.session().id());

            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }
        return "";
    }



}

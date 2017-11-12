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

import com.webcheckers.appl.GameCenter;

public class GetGamePageRoute implements Route {

    static final String VIEW_NAME = "game.ftl";

//    static final String CURRENT_PLAYER_ATTR = "currentPlayer";
    static final String VIEWMODE_ATTR = "viewMode";
//    static final String RED_PLAYER_ATTR = "redPlayer";
//    static final String WHITE_PLAYER_ATTR = "whitePlayer";
//    static final String ACTIVE_COLOR_ATTR = "activeColor";
    static final String TITLE = "Web Checkers";
    static final String GAME_BOARD_ATTR = "gameBoard";

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

        com.webcheckers.model.Player ploop = new com.webcheckers.model.Player("blub");
        com.webcheckers.model.Player red = new com.webcheckers.model.Player("red");
        com.webcheckers.model.Player blue = new com.webcheckers.model.Player("blue");
        com.webcheckers.model.CheckersBoard board = new com.webcheckers.model.CheckersBoard(red, blue);

        final Map<String, Object> vm = new HashMap<>();
        // vm.put(GetHomeRoute.TITLE_ATTR, TITLE);//TODO
        // vm.put(CURRENT_PLAYER_ATTR, ploop);
        vm.put(VIEWMODE_ATTR, "Unknown");
        // vm.put(RED_PLAYER_ATTR, red);
        // vm.put(WHITE_PLAYER_ATTR, blue);
        // vm.put(ACTIVE_COLOR_ATTR, "RED PLAYER GOES");
        vm.put(GAME_BOARD_ATTR, board);

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }



}

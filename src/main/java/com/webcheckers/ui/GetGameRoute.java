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

public class GetGameRoute implements Route {

    static final String VIEW_NAME = "game_form.ftl";

    private final TemplateEngine templateEngine;

    GetGameRoute(final TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
    }

    public String handle(Request request, Response response) {
        final Session httpSession = request.session();
//        final PlayerServices playerServices = httpSession.attribute(GetHomeRoute.PLAYERSERVICES_KEY);
//        GuessGame game = playerServices.currentGame();
//
        final Map<String, Object> vm = new HashMap<>();
//        final Map<String, Object> vm = new HashMap<>();
//        vm.put(GetHomeRoute.TITLE_ATTR, TITLE);
//        vm.put(GAME_BEGINS_ATTR, game.isGameBeginning());

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }



}

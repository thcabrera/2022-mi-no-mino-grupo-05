package controllers;

import domain.entidades.Documentacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserController {
    public ModelAndView pantallaPrincipal(Request request, Response response) {
        return new ModelAndView(null, "user/us_principal.hbs");
    }

}

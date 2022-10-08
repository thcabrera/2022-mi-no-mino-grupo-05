package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {
    public ModelAndView pantallaPrincipal(Request request, Response response) {
        return new ModelAndView(null, "user/us_principal.hbs");
    }
}

package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.Spark.*;

public class ControllerDefault {
        public ModelAndView saludoController(Request request, Response response){
            return new ModelAndView(null, "prueba/prueba.hbs");
        }
}

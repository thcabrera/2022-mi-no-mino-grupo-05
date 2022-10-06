package controllers;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.Spark.*;

public class ControllerDefault {
        public String saludoController(Request request, Response response){
            return "hola mundo!";
        }
}

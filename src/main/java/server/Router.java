package server;

import controllers.ControllerDefault;
import controllers.OrganizacionesController;
import controllers.TrayectosController;
import controllers.UserController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

import static spark.Spark.staticFiles;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        ControllerDefault controllerDefault= new ControllerDefault();
        OrganizacionesController organizacionesController = new OrganizacionesController();
        UserController userController = new UserController();
        TrayectosController trayectosController = new TrayectosController();

        /*----------- user ---------- */
        Spark.path("/user", () -> {

            Spark.path("/principal", () -> {
                Spark.get("", userController::pantallaPrincipal, engine);
            });

            /*----------- Trayecto y tramos ---------- */
            Spark.path("/registrar_trayecto", () -> {
                Spark.get("", trayectosController::pantallaRegistrarTrayecto, engine);
            });

            Spark.path("/t_limpio", () -> {
                Spark.get("", trayectosController::pantallaRegistrarTramoLimpio, engine);
                Spark.post("", trayectosController::guardarTramoLimpio);
            });

            Spark.path("/t_publico", () -> {
                Spark.get("", trayectosController::pantallaRegistrarTramoPublico, engine);
                Spark.post("", trayectosController::guardarTramoLimpio);
            });

            Spark.path("/t_contratado", () -> {
                Spark.get("", trayectosController::pantallaRegistrarTramoContratado, engine);
                Spark.post("", trayectosController::guardarTramoLimpio);
            });

        });




        /*----------- Ejemplos ---------- */
        Spark.get("/hola", controllerDefault::saludoController);
        Spark.get("/saluda", (request, response) -> "hola" + request.queryParams("nombre"));

        Spark.path("/organizaciones", ()-> {
            Spark.get("/:id", organizacionesController::mostrar, engine);
        });

        // configure image paths
        Spark.get("/hola", controllerDefault::saludoController);
    }
}

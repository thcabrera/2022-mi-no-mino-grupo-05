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
            Spark.path("/trayectos", () -> {
                Spark.path("/registrar", () -> {
                    // pantalla principal de registrar
                    Spark.get("", trayectosController::pantallaRegistrarTrayecto, engine);
                    // limpio
                    Spark.path("/limpio", () -> {
                        Spark.get("", trayectosController::pantallaRegistrarTramoLimpio, engine);
                        Spark.post("", trayectosController::guardarTramoLimpio);
                    });
                    // publico
                    Spark.path("/publico", () -> {
                        Spark.get("", trayectosController::pantallaRegistrarTramoPublico, engine);
                        Spark.post("", trayectosController::guardarTramoLimpio);
                    });
                    // contratado
                    Spark.path("/contratado", () -> {
                        Spark.get("", trayectosController::pantallaRegistrarTramoContratado, engine);
                        Spark.post("", trayectosController::guardarTramoLimpio);
                    });
                });
                Spark.path("/modificar", () -> {
                    Spark.get("", (req, resp) -> "Modificar trayectos!");
                });
            });

            /*----------- Organizaciones ejemplo ---------- */
            Spark.path("/mis_organizaciones", () ->{
                Spark.get("", organizacionesController::mostrarTodos, engine);
                Spark.get("/solicitar_alta", (req, response) -> "Solicitando alta!");
            });

            Spark.get("/reportes", (req, resp) -> "Visualizando reportes! xd");

            Spark.get("/ejecutar_calculadora", (req, resp) -> "Ejecutando calculadora!");

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

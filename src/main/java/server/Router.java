package server;

import controllers.*;
import domain.entidades.Organizacion;
import domain.entidades.Persona;
import domain.usuarios.Rol;
import domain.usuarios.Usuario;
import helpers.RolHelper;
import helpers.UsuarioHelper;
import middlewares.AuthMiddleware;
import models.RepositorioDeTramosEnMemoria;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.*;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .withHelper("incrementar", NumberHelper.incrementar)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        RepositorioDeTramosEnMemoria rTramos = new RepositorioDeTramosEnMemoria();
        ControllerDefault controllerDefault= new ControllerDefault();
        OrganizacionesController organizacionesController = new OrganizacionesController();
        UserController userController = new UserController();
        TrayectosController trayectosController = new TrayectosController();
        trayectosController.setRepositorioDeTramos(rTramos);
        TramosController tramosController = new TramosController();
        tramosController.setRepositorioDeTramos(rTramos);
        LoginController loginController = new LoginController();
        UtilidadesController utilidadesController = new UtilidadesController();

        /*-------- Manejo del Login -------*/
        Spark.path("/login", ()->{
            Spark.get("", loginController::pantallaLogin , engine);
            Spark.post("", loginController::login);
            Spark.get("/2", loginController::loginIncorrecto, engine);
        });

        /*----------- user ---------- */
        Spark.path("/user", () -> {

            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", ((request, response) -> {
                if (!RolHelper.usuarioTieneRol(request, Rol.PERSONA)){
                    response.redirect("/404");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if (!RolHelper.usuarioTieneRol(request, Rol.PERSONA)){
                    response.redirect("/404");
                    Spark.halt();
                }
           }));

            Spark.path("/principal", () -> {
                Spark.get("", userController::pantallaPrincipal, engine);
            });

            /*----------- Trayecto y tramos ---------- */
            Spark.path("/trayectos", () -> {
                Spark.get("", trayectosController::mostrarTrayectos, engine);
                Spark.post("/agregar", trayectosController::agregarTrayecto);
                Spark.get("/editar/:idTrayecto", trayectosController::pantallaEditarTrayecto, engine);
                Spark.post("/editar/:idTrayecto", trayectosController::editarTrayecto);
                Spark.post("/eliminar/:idTrayecto", trayectosController::eliminarTrayecto);
                // las rutas de los tramos necesitan llevar el id del trayecto para poder
                // redirigir a la pantalla de edicion del trayecto concreto al confirmar la operacion
                Spark.path("/tramo", () -> {
                    // el eliminar tramo se puede hacer como un eliminar generico, independiente del tipo
                    Spark.post("/eliminar/:idTrayecto/:idTramo", tramosController::eliminarTramo);
                    // el editar generico deberia hacer un if con las subclases y redireccionar a la pantalla especifica
                    Spark.get("/editar/:idTrayecto/:idTramo", (rq, rs) -> "Editar tramo!");
                    // limpio
                    Spark.path("/limpio", () -> {
                        Spark.get("/agregar/:idTrayecto", tramosController::pantallaRegistrarTramoLimpio, engine);
                        Spark.post("/agregar/:idTrayecto", tramosController::guardarTramoLimpio);
                    });
                    // publico
                    Spark.path("/publico", () -> {
                        Spark.get("", tramosController::pantallaRegistrarTramoPublico, engine);
                        Spark.post("", tramosController::guardarTramoLimpio);
                    });
                    // contratado
                    Spark.path("/contratado", () -> {
                        Spark.get("", tramosController::pantallaRegistrarTramoContratado, engine);
                        Spark.post("", tramosController::guardarTramoLimpio);
                    });
                });
            });

            /*----------- Organizaciones ejemplo ---------- */
            Spark.path("/mis_organizaciones", () ->{
                Spark.post("/delete/:id", organizacionesController::darDeBaja);
                Spark.get("", organizacionesController::mostrarTodos, engine);
                Spark.get("/solicitar_alta", organizacionesController::solicitarAlta, engine);
                Spark.post("/solicitar_alta/enviar", organizacionesController::recibirSolicitudDeAlta);
            });

            Spark.get("/reportes", (req, resp) -> "Visualizando reportes! xd");

            Spark.get("/ejecutar_calculadora", (req, resp) -> "Ejecutando calculadora!");

        });

        Spark.path("/admin", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", ((request, response) -> {
                if (! RolHelper.usuarioTieneRol(request, Rol.ADMINISTRADOR)){
                    response.redirect("/404");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if (! RolHelper.usuarioTieneRol(request, Rol.ADMINISTRADOR)){
                    response.redirect("/404");
                    Spark.halt();
                }
            }));

        });

        Spark.path("/organizacion", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", ((request, response) -> {
                if (!RolHelper.usuarioTieneRol (request, Rol.ORGANIZACION)){
                    response.redirect("/404");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if (!RolHelper.usuarioTieneRol (request, Rol.ORGANIZACION)){
                    response.redirect("/404");
                    Spark.halt();
                }
            }));

        });

        Spark.path("/agente_sectorial", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", (((request, response) -> {
                if (!RolHelper.usuarioTieneRol(request, Rol.AGENTE_SECTORIAL)){
                    response.redirect("/404");
                    Spark.halt();
                }
            })));
            Spark.before("/*", (((request, response) -> {
                if (!RolHelper.usuarioTieneRol(request, Rol.AGENTE_SECTORIAL)){
                    response.redirect("/404");
                    Spark.halt();
                }
            })));

        });

        Spark.path("/utilidades", () -> {
            Spark.get("/municipios/:idProvincia", utilidadesController::obtenerMunicipios, new JsonTransformer());
            Spark.get("/localidades/:idMunicipio", utilidadesController::obtenerLocalidades, new JsonTransformer());
            Spark.get("/areas/:idOrganizacion", utilidadesController::obtenerAreas, new JsonTransformer());
        });

        /*----------- Ejemplos ---------- */
        Spark.get("/hola", controllerDefault::saludoController);
        Spark.get("/saluda", (request, response) -> "hola" + request.queryParams("nombre"));

        Spark.path("/organizaciones", ()-> {
            Spark.get("/:id", organizacionesController::mostrar, engine);
        });

        // configure image paths
        Spark.get("/hola", controllerDefault::saludoController);

        Spark.get("/404", ((request, response) -> "ACCESO DENEGADO!"));

//        Spark.path("/utilidades", () ->{
//            Spark.get("/deptos/:idProv", )
//        });

    }
}

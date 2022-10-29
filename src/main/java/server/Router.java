package server;

import controllers.*;
import domain.usuarios.Rol;
import helpers.RolHelper;
import middlewares.AutMiddleware;
import middlewares.AuthMiddleware;
import models.enMemoria.RepositorioDeTramosEnMemoria;
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
        ReportesController reportesController = new ReportesController();
        SolicitudesController solicitudesController = new SolicitudesController();
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
            Spark.before("", AutMiddleware::verificarPersona);
            Spark.before("/*", AutMiddleware::verificarPersona);

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

            Spark.get("/reportes", reportesController::mostrarReportesUsuario, engine);

            Spark.get("/ejecutar_calculadora", (req, resp) -> "Ejecutando calculadora!");

            /*----------- Reportes  ---------- */
        });

        Spark.path("/admin", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", AutMiddleware::verificarAdministrador);
            Spark.before("/*", AutMiddleware::verificarAdministrador);
        });

        Spark.path("/organizacion", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", ((request, response) -> {
                if (!RolHelper.usuarioTieneRol(request, Rol.ORGANIZACION)) {
                    response.redirect("/404");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if (!RolHelper.usuarioTieneRol(request, Rol.ORGANIZACION)) {
                    response.redirect("/404");
                    Spark.halt();
                }
            }));
            Spark.path("/principal", () -> {
                Spark.get("", organizacionesController::pantallaPrincipal, engine);
            });

            Spark.path("/alta_area", () -> {
                Spark.get("/:idOrg", organizacionesController::darDeAltaArea, engine);
            });

            Spark.path("/reportes", () -> {
                Spark.get("", reportesController::mostrarReportesOrganizacion, engine);
                Spark.get("/:idPeriodicidad", reportesController::mostrarReportesOrganizacionConPeriodicidad, engine);
            });

            Spark.path("/solicitudes", () -> {
                Spark.get("/:idOrg", solicitudesController::mostrarTodasParaOrg, engine);
                Spark.path("/:idOrg", () -> {
                    Spark.post("/rechazar/:idSol", solicitudesController::eliminar);
                    Spark.post("/aceptar/:idSol", solicitudesController::aceptar);

                });
            });

        });


/*
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
<<<<<<< HEAD
=======

        });
=======
>>>>>>> 9a0b21097ee1aa9402f8d39f36e75e7c45164253

            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", AutMiddleware::verificarOrganizacion);
            Spark.before("/*", AutMiddleware::verificarOrganizacion);

<<<<<<< HEAD
        });
>>>>>>> d74b622bd8210a71043ae29a3d46467cd2bd9191

 */

        Spark.path("/agente_sectorial", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", AutMiddleware::verificarAgenteSectorial);
            Spark.before("/*", AutMiddleware::verificarAgenteSectorial);

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

        Spark.get("/404", ((request, response) -> "NO EXISTE LA PAG!"));
        Spark.get("/403", ((request, response) -> "ACCESO DENEGADO!"));

//        Spark.path("/utilidades", () ->{
//            Spark.get("/deptos/:idProv", )
//        });
        Spark.get("", utilidadesController::pantallaClientePerdido, engine);
        Spark.get("/*", utilidadesController::pantallaClientePerdido, engine);

    }
}

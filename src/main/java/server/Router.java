package server;

import controllers.*;
import domain.usuarios.Rol;
import helpers.RolHelper;
import middlewares.AutMiddleware;
import middlewares.AuthMiddleware;
import org.checkerframework.checker.units.qual.C;
import repositorios.RecomendacionesController;
import repositorios.enMemoria.RepositorioDeTramosEnMemoria;
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
        ControllerDefault controllerDefault= new ControllerDefault();
        OrganizacionesController organizacionesController = new OrganizacionesController();
        UserController userController = new UserController();
        TrayectosController trayectosController = new TrayectosController();
        TramosController tramosController = new TramosController();
        LoginController loginController = new LoginController();
        UtilidadesController utilidadesController = new UtilidadesController();
        ReportesController reportesController = new ReportesController();
        SolicitudesController solicitudesController = new SolicitudesController();
        AdministradorController administradorController = new AdministradorController();
        MedicionesController medicionesController = new MedicionesController();
        CalculadoraHCController calculadoraHCController = new CalculadoraHCController();
        RecomendacionesController recomendacionesController = new RecomendacionesController();

        /*-------- Manejo del Login -------*/
        Spark.path("/login", ()->{
            Spark.get("", loginController::pantallaLogin , engine);
            Spark.post("", loginController::login, engine);

            Spark.get("/create", loginController::pantallacrearUser, engine);
            Spark.post("/create" , loginController::crearUsuario, engine);
        });

        Spark.get("/logout", loginController::logout);

        /*----------- user ---------- */
        Spark.path("/user", () -> {


            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", AutMiddleware::verificarPersona);
            Spark.before("/*", AutMiddleware::verificarPersona);

            Spark.path("/principal", () -> {
                Spark.get("", userController::pantallaPrincipal, engine);
            });
            Spark.get("/calculadora_hc", calculadoraHCController::pantallaCalculadoraHC, engine);
            /*----------- Trayecto y tramos ---------- */
            Spark.path("/trayectos", () -> {
                Spark.get("", trayectosController::mostrarTrayectos, engine);
                Spark.get("/agregar", trayectosController::pantallaAgregarTrayecto, engine);
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
                    Spark.get("/editar/:idTrayecto/:idTramo", tramosController::editarTramo);
                    // limpio
                    Spark.path("/limpio", () -> {
                        Spark.get("/crear/:idTrayecto", tramosController::pantallaRegistrarTramoLimpio, engine);
                        Spark.post("/crear/:idTrayecto", tramosController::guardarTramoLimpio);
                        Spark.get("/editar/:idTrayecto/:idTramo", tramosController::editarTramoLimpio, engine);
                        Spark.post("/modificar/:idTrayecto/:idTramo", tramosController::modificarTramoLimpio);
                    });
                    // publico
                    Spark.path("/publico", () -> {
                        Spark.get("/crear/:idTrayecto", tramosController::pantallaRegistrarTramoPublico, engine);
                        Spark.post("", tramosController::guardarTramoPublico);
                    });
                    // contratado
                    Spark.path("/contratado", () -> {
                        Spark.get("/crear/:idTrayecto", tramosController::pantallaRegistrarTramoContratado, engine);
                        Spark.post("/crear/:idTrayecto", tramosController::guardarTramoContratado);
                        Spark.get("/editar/:idTrayecto/:idTramo", tramosController::editarTramoContratado, engine);
                        Spark.post("/modificar/:idTrayecto/:idTramo", tramosController::modificarTramoContratado);
                    });
                    // particular
                    Spark.path("/particular", () -> {
                        Spark.get("/crear/:idTrayecto", tramosController::pantallaRegistrarTramoParticular, engine);
                        Spark.post("/crear/:idTrayecto", tramosController::guardarTramoParticular);
                    });
                    // unirse a compartido
                    Spark.path("/compartido", () -> {
                        Spark.get("/unirse/:idTrayecto", tramosController::pantallaRegistrarTramoCompartido, engine);
                        Spark.post("/unirse/:idTrayecto", tramosController::unirseTramoCompartido);
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

            /*----------- Reportes  ---------- */
        });

        Spark.path("/administrador", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", AutMiddleware::verificarAdministrador);
            Spark.before("/*", AutMiddleware::verificarAdministrador);
            Spark.path("/principal", () -> {
                Spark.get("", administradorController::pantallaPrincipal, engine);
            });

            Spark.path("/organizaciones", () -> {

                Spark.get("", administradorController::pantallaVerOrganizaciones, engine);
                Spark.get("/agregar", administradorController::pantallaAgregarOrganizacion, engine);
                Spark.post("/agregar", administradorController::agregarOrganizacion);
                Spark.post("/eliminar/:idOrg", administradorController::eliminarOrganizacion);

            });

            Spark.path("/lineas", () -> {



            });

            Spark.path("/parametros_generales", () -> {

            });

        });

        Spark.path("/organizacion", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.before("", AutMiddleware::verificarOrganizacion);
            Spark.before("/*", AutMiddleware::verificarOrganizacion);
            Spark.path("/principal", () -> {
                Spark.get("", organizacionesController::pantallaPrincipal, engine);
            });

            Spark.path("/alta_area", () -> {
                Spark.get("", organizacionesController::darDeAltaArea, engine);
                Spark.post("", organizacionesController::agregarArea);
            });

            Spark.path("/reportes", () -> {
                Spark.get("", reportesController::mostrarReportesOrganizacion, engine);
                Spark.get("/:idPeriodicidad", reportesController::mostrarReportesOrganizacionConPeriodicidad, engine);
            });

            Spark.path("/solicitudes", () -> {
                Spark.get("", solicitudesController::mostrarTodasParaOrg, engine);
                Spark.path("/:idOrg", () -> {
                    Spark.post("/rechazar/:idSol", solicitudesController::eliminar);
                    Spark.post("/aceptar/:idSol", solicitudesController::aceptar);

                });
            });
            Spark.path("/cargar_mediciones", () -> {
                Spark.get("", medicionesController::mostrarCargarMediciones, engine);
                Spark.post("", medicionesController::cargarMediciones);

            });

        });

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
            Spark.get("/lineas/:idTipoTransporte", utilidadesController::obtenerLineas, new JsonTransformer());
            Spark.get("/paradas/:id_linea", utilidadesController::obtenerParadas, new JsonTransformer());
            Spark.get("/paradasDestino/:id_linea/:id_paradaPartida", utilidadesController::obtenerParadasDestino, new JsonTransformer());
            Spark.get("/tramos/:idPropietario/:idOrganizacion", utilidadesController::obtenerTramosCompartidosPersona, new JsonTransformer());


        });

        /*------Envio de Recomendaciones -------*/
        Spark.path("/recomendaciones", () -> {
            Spark.get("", recomendacionesController::mostrarRecomendaciones, engine);
        });

        /*----------- Ejemplos ---------- */
        Spark.get("/hola", controllerDefault::saludoController, engine);
        Spark.get("/saluda", (request, response) -> "hola" + request.queryParams("nombre"));

        Spark.path("/organizaciones", ()-> {
            Spark.get("/:id", organizacionesController::mostrar, engine);
        });

        Spark.get("/404", utilidadesController::pantallaClientePerdido, engine);
        Spark.get("/403", utilidadesController::pantallaAccesoDenegado, engine);

        Spark.get("/400", ((request, response) -> {
            response.status(400);
            return "ERROR!";
        }));

//        Spark.path("/utilidades", () ->{
//            Spark.get("/deptos/:idProv", )
//        });
        Spark.get("", utilidadesController::pantallaClientePerdido, engine);
        Spark.get("/*", utilidadesController::pantallaClientePerdido, engine);

    }
}

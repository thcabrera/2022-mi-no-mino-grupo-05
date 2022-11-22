package server;

import domain.services.envioCorreo.ProgramadorDeRecomendaciones;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {

    // configuramos las tareas cron
    public static void configurar(){
        ProgramadorDeRecomendaciones.programar();
    }

    public static void main(String[] args) {
        Spark.port(9000); // si queres que sea local reemplazalo por 9000
        Router.init();
        DebugScreen.enableDebugScreen();
        configurar();
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


}

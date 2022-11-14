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
        Spark.port(9000);
        Router.init();
        DebugScreen.enableDebugScreen();
        configurar();
    }


}

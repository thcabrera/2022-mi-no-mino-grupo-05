package controllers;

import async.TareaDeImportacion;
import async.TaskManager;
import domain.entidades.Organizacion;
import helpers.UsuarioHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class    MedicionesController {

    public ModelAndView mostrarCargarMediciones(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        System.out.println();
        return new ModelAndView(parametros, "org/org_registrar_mediciones.hbs");
    }

    public Response cargarMediciones(Request request, Response response) {
        Organizacion organizacion = (Organizacion) UsuarioHelper.usuarioLogueado(request).getActor();
        String path = cargarArchivo(request);
        if (path != null){
            // obtengo el Task Manager (Singleton) y agrego a la cola de tareas la importacion de la medicion
            TaskManager administradorDeTareas = TaskManager.obtenerTaskManager();
            administradorDeTareas.agregarALaCola(new TareaDeImportacion(path, organizacion));
        }
        response.redirect("/organizacion/principal");
        return response;
    }

    // el nombre lo formaremos por MEDICIONES-UNIXTIME.xlsx
    private String obtenerNombreDeArchivo(){
        return String.format("MEDICIONES-%d.xlsx",
                Instant.now().getEpochSecond());
    }

    private String cargarArchivo(Request request) {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        try (InputStream is = request.raw().getPart("archivo_mediciones").getInputStream()) {
            // Use the input stream to create a file
            String pathname = "tempfiles/" + obtenerNombreDeArchivo();
            File file = new File(pathname);
            copyInputStreamToFile(is, file);
            return pathname;
        } catch(IOException | ServletException e){
            return null;
        }
    }
}

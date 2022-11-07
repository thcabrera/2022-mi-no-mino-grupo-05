package controllers;

import domain.db.EntityManagerHelper;
import domain.entidades.Organizacion;
import domain.mediciones.consumos.MedioTransporte;
import domain.mediciones.consumos.actividades.Actividad;
import domain.mediciones.consumos.actividades.ActividadConsumo;
import domain.mediciones.consumos.actividades.Logistica;
import domain.mediciones.consumos.actividades.TipoActividadConsumo;
import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.importador.ImportarExcel;
import helpers.UsuarioHelper;
import repositorios.RepositorioActividades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;
import static org.apache.commons.io.FileUtils.moveFile;

public class    MedicionesController {
    private ImportarExcel importador;
    private RepositorioActividades repositorioActividades  = new RepositorioActividades();

    public ModelAndView mostrarCargarMediciones(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        System.out.println();
        return new ModelAndView(parametros, "org/org_registrar_mediciones.hbs");
    }

    public Map<String, TipoConsumo> filtrarPorTipoActividad(List<TipoConsumo> tipoConsumos,
                                                            TipoActividadConsumo tipoActividadConsumo){
        return tipoConsumos
                .stream()
                .filter(c -> c.getTipoActividad().equals(tipoActividadConsumo))
                .collect(Collectors.toMap(TipoConsumo::getDescripcion, item -> item));
    }

    public Response cargarMediciones(Request request, Response response) throws IOException {
        Organizacion organizacion = (Organizacion) UsuarioHelper.usuarioLogueado(request).getActor();

        String archivo_path = "archivo_temporal/archivo.xlsx"; // + request.queryParams("archivo_mediciones"); // com odeberia funcar
        List<TipoConsumo> tiposConsumos = EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + TipoConsumo.class.getName())
                .getResultList();
        List<MedioTransporte> medioTransportes = EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + MedioTransporte.class.getName())
                .getResultList();

        Map<String, MedioTransporte> medioTransporteMap = medioTransportes
                .stream().collect(Collectors.toMap(MedioTransporte::getDescripcion, m -> m));

        Map<String, TipoConsumo> tiposConsumosFijos = filtrarPorTipoActividad(tiposConsumos,
                TipoActividadConsumo.COMBUSTION_FIJA);

        Map<String, TipoConsumo> tiposConsumosMoviles = filtrarPorTipoActividad(tiposConsumos,
                TipoActividadConsumo.COMBUSTION_MOVIL);

        Map<String, TipoConsumo> tiposConsumosElectricidad = filtrarPorTipoActividad(tiposConsumos,
                TipoActividadConsumo.ELECTRICIDAD);

        System.out.println( " archivo path: "+  archivo_path);
        importador = new ImportarExcel(
                tiposConsumosFijos,
                tiposConsumosMoviles,
                tiposConsumosElectricidad,
                medioTransporteMap);
        List<Actividad> mediciones = importador.importar(archivo_path);
        if (mediciones != null){
            // agregamos las mediciones a la org
            organizacion.agregarMediciones(mediciones);
            // persistimos las actividades
            EntityManagerHelper.getEntityManager().getTransaction().begin();
            mediciones.forEach(m -> EntityManagerHelper.getEntityManager().persist(m));
            EntityManagerHelper.getEntityManager().getTransaction().commit();
            // TODO esto se podría reemplazar por una pegada con fetch mediante javascript
            response.redirect("/organizacion/principal?msg=importacion-exitosa");
        }
        response.redirect("/organizacion/principal?msg=importacion-fallida");
        Date fecha = new Date();
        moveFile(new File("archivo_temporal/archivo.xlsx"), new File("archivos_cargados/archivo"+fecha+".xlsx" ));
        return response;
    }

    public Response moverArchivo(Request request, Response response) throws ServletException, IOException {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        try (InputStream is = request.raw().getPart("archivo_mediciones").getInputStream()) {
            // Use the input stream to create a file
            File file = new File("archivo_temporal/archivo.xlsx");
            copyInputStreamToFile(is, file);
        }
        System.out.println("File uploaded");
        return this.cargarMediciones(request, response);
    }
}

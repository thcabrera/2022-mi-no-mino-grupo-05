package controllers;

import domain.entidades.*;
import models.RepositorioDeAreasEnMemoria;
import models.RepositorioDeOrganizacionesEnMemoria;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import domain.db.EntityManagerHelper;
import java.util.HashMap;
import java.util.Map;

public class OrganizacionesController {
    //ABM: A->Alta |B-> Baja |M-> Modificaco  |L->Listado | V->Visualizar

    private RepositorioDeAreasEnMemoria repositorioDeAreas = new RepositorioDeAreasEnMemoria();

    private RepositorioDeOrganizacionesEnMemoria repositorioDeOrganizaciones = new RepositorioDeOrganizacionesEnMemoria();

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");

        Organizacion organizacion = EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, new Integer(idBuscado));
        return new ModelAndView(new HashMap<String, Object>() {{
            put("servicio", idBuscado);

        }}, "organizaciones/organizacion.hbs");

    }

    public ModelAndView solicitarAlta(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("organizaciones",repositorioDeOrganizaciones.buscarTodos());
        parametros.put("areas",repositorioDeAreas.buscarTodos());
        return new ModelAndView(parametros, "user/us_solicitar_alta.hbs");
    }

    public Response recibirSolicitudDeAlta(Request request, Response response){
        int idOrg = Integer.parseInt(request.queryParams("idOrg"));
        int idArea = Integer.parseInt(request.queryParams("idArea"));
        Area area = repositorioDeAreas.buscar(idArea);
        Organizacion organizacion =  repositorioDeOrganizaciones.buscar(idOrg);
        organizacion.recibirSolicitud(new Solicitud(null, area)); // TODO falta que la persona se envie como solicitante
        response.redirect("/user/principal");
        return response;
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
//        List<Organizacion> usuarios = this.repo.buscarTodos();
        parametros.put("areas", repositorioDeAreas.buscarTodos());
//        asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "user/us_organizaciones.hbs");
    }


    public Response darDeBaja(Request request, Response response){
        int areaId = Integer.parseInt(request.params("id"));
        Area area = repositorioDeAreas.buscar(areaId);
        repositorioDeAreas.eliminar(area);
        response.redirect("/user/mis_organizaciones");
        return response;
    }

}

package controllers;

import domain.entidades.*;
import helpers.UsuarioHelper;
import models.RepositorioDeAreas;
import models.RepositorioDeOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import domain.db.EntityManagerHelper;
import java.util.HashMap;
import java.util.Map;

public class OrganizacionesController {
    //ABM: A->Alta |B-> Baja |M-> Modificaco  |L->Listado | V->Visualizar

    private RepositorioDeAreas repositorioDeAreas = new RepositorioDeAreas();

    private RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();

    public ModelAndView mostrar(Request request, Response response) {
        int idOrganizacion = Integer.parseInt(request.params("id"));

        Organizacion organizacion = repositorioDeOrganizaciones.buscar(idOrganizacion);
        return new ModelAndView(new HashMap<String, Object>() {{
            put("servicio", idOrganizacion);

        }}, "organizaciones/organizacion.hbs");

    }

    public ModelAndView solicitarAlta(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("organizaciones",repositorioDeOrganizaciones.buscarTodos());
        return new ModelAndView(parametros, "user/us_solicitar_alta.hbs");
    }

    public Response recibirSolicitudDeAlta(Request request, Response response){
        int idOrg = Integer.parseInt(request.queryParams("id_org"));
        int idArea = Integer.parseInt(request.queryParams("id_area"));
        Area area = repositorioDeAreas.buscar(idArea);
        // validamos que el area exista
        if (area == null){
            System.out.printf("NO EXISTE AREA DE ID %d\n", idArea);
            response.redirect("/user/principal");
            return response;
        }
        Organizacion organizacion =  repositorioDeOrganizaciones.buscar(idOrg);
        // validamos que la org exista
        if (organizacion == null) {
            System.out.printf("NO EXISTE ORGANIZACION DE ID %d\n", idOrg);
            response.redirect("/user/principal");
            return response;
        }
        // validamos que el area pertenezca a la organizacion
        if (!organizacion.getAreas().contains(area)){
            System.out.println("AREA " + area.getNombre() + " NO PERTENECE A LA ORGANIZACION "
                    + organizacion.getNombre());
            response.redirect("/user/principal");
            return response;
        }
        // obtenemos a la persona del usuario
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
        persona.altaAceptada(area);
        // TODO #2 si la persona ya envió una solicitud para esa area, le informamos?
        if (organizacion.getSolicitudDe(persona, area) != null){
            System.out.println("EL USUARIO YA LE ENVIO UNA SOLICITUD A ESTA ORGANIZACION");
            response.redirect("/user/principal");
            return response;
        }
        // instanciamos a la solicitud
        Solicitud solicitud = new Solicitud(persona, area);
        solicitud.setOrganizacion(organizacion);
        solicitud.setEstado(EstadoSolicitud.ENVIADA);
        // la agregamos a la organizacion
        organizacion.recibirSolicitud(solicitud);
        // persistimos la solicitud
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(solicitud);
        EntityManagerHelper.getEntityManager().getTransaction().commit();

        for(Persona p: area.getMiembros())
            System.out.println("PERSONAS");
        response.redirect("/user/principal");
        return response;
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
        parametros.put("areas", persona.obtenerOrganizaciones());
        return new ModelAndView(parametros, "user/us_organizaciones.hbs");
    }

    public Response darDeBaja(Request request, Response response){
        int areaId = Integer.parseInt(request.params("id"));
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
        persona.getListaAreas().remove(repositorioDeAreas.buscar(areaId));
        response.redirect("/user/mis_organizaciones");
        return response;
    }

}

package controllers;

import domain.entidades.*;
import helpers.UsuarioHelper;
import repositorios.RepositorioDeAreas;
import repositorios.RepositorioDeOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import domain.db.EntityManagerHelper;

import java.util.*;
import java.util.stream.Collectors;

public class OrganizacionesController {
    //ABM: A->Alta |B-> Baja |M-> Modificaco  |L->Listado | V->Visualizar

    private RepositorioDeAreas repositorioDeAreas = new RepositorioDeAreas();

    private RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();

    public ModelAndView pantallaPrincipal(Request request, Response response) {
        String msg = request.queryParams("msg");
        Map<String, Object> parametros = new HashMap<>();
        if (msg != null)
            parametros.put(msg, true);
        return new ModelAndView(parametros, "org/org_principal.hbs");
    }

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
        parametros.put("areas", persona.getListaAreas());
        return new ModelAndView(parametros, "user/us_organizaciones.hbs");
    }

    public Response darDeBaja(Request request, Response response){
        int areaId = Integer.parseInt(request.params("id"));
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
        persona.getListaAreas().remove(repositorioDeAreas.buscar(areaId));
        response.redirect("/user/mis_organizaciones");
        return response;
    }

    public ModelAndView darDeAltaArea(Request request, Response response) {
        UtilidadesController utlidadesController = new UtilidadesController();
        Organizacion organizacion = (Organizacion) UsuarioHelper.usuarioLogueado(request).getActor();
        int idOrganizacion = organizacion.getId();
        //int idOrganizacion = Integer.parseInt(request.params("idOrg"));
        Map<String, Object> parametros = new HashMap<>();
        List<Area.AreaDTO> listaAreasVacia = new ArrayList<>();
        //List<Area> areas =  this.repositorioDeAreas.buscarTodasLasAreas(idOrganizacion);
        List<Area.AreaDTO> listaAreas = this.obtenerAreasDTO(idOrganizacion);
        if(listaAreas == null){
            parametros.put("areas", listaAreasVacia);
            return new ModelAndView(parametros, "org/org_alta_area.hbs");
        }else {
            parametros.put("areas",listaAreas);
            return new ModelAndView(parametros, "org/org_alta_area.hbs");
        }
    }

    public List<Area.AreaDTO> obtenerAreasDTO(Integer idOrg){
        Organizacion organizacion = repositorioDeOrganizaciones.buscar(idOrg);
        if (organizacion == null)
            return new ArrayList<>();
        return organizacion.getAreas().stream().map(Area::convertirADTO).collect(Collectors.toList());
    }

    public Response agregarArea(Request request, Response response){
        try{
            String nombre = request.queryParams("nombre");
            String nombreALLCAPS = nombre.toUpperCase();
            Organizacion organizacion = (Organizacion) UsuarioHelper.usuarioLogueado(request).getActor();
            Area area = new Area(
                    nombreALLCAPS,
                    organizacion //nose como ponerla (ya aprendimos)
            );
            repositorioDeAreas.guardar(area);
            response.redirect("/organizacion/alta_area");
            return response;
        } catch(NumberFormatException e){
            response.redirect("/404");
        }
        return response;
    }
}

package controllers;

import domain.Direccion;
import domain.db.EntityManagerHelper;
import domain.entidades.Localidad;
import domain.entidades.Municipio;
import domain.entidades.Organizacion;
import domain.entidades.Persona;
import domain.viaje.Tramo;
import domain.viaje.Trayecto;
import helpers.UsuarioHelper;
import lombok.Setter;
import repositorios.RepositorioDeOrganizaciones;
import repositorios.RepositorioDeTramos;
import repositorios.RepositorioDeTrayectos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
public class TrayectosController {

    private RepositorioDeTramos repositorioDeTramos = new RepositorioDeTramos();
    private RepositorioDeTrayectos repositorioDeTrayectos = new RepositorioDeTrayectos();
    private RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();

    public ModelAndView pantallaEditarTrayecto(Request request, Response response) {
        int idTrayecto = Integer.parseInt(request.params("idTrayecto"));
        Trayecto trayecto = repositorioDeTrayectos.buscar(idTrayecto);
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
        // si la persona no es la propietaria del trayecto, reportada
        if (! persona.equals(trayecto.getPersona())){
            response.redirect("/403");
        }
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tramos", trayecto.getTramos()
                .stream().map(Tramo::convertirADTO)
                .collect(Collectors.toList()));
        parametros.put("idTrayecto", idTrayecto);
        parametros.put("organizacion", trayecto.getOrganizacion());
        parametros.put("distanciaTrayecto", String.format("%.2f", trayecto.obtenerDistanciaTotal()));
        return new ModelAndView(parametros, "trayectos/us_editar_trayecto.hbs");
    }

    public Response editarTrayecto(Request request, Response response){
        int idTrayecto = Integer.parseInt(request.params("idTrayecto"));
        int idOrg = Integer.parseInt(request.queryParams("idOrg"));
        Trayecto trayecto = this.repositorioDeTrayectos.buscar(idTrayecto);
        trayecto.setOrganizacion(this.repositorioDeOrganizaciones.buscar(idOrg));
        response.redirect("/user/trayectos");
        return response;
    }

    public ModelAndView pantallaAgregarTrayecto(Request request, Response response) {
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("organizaciones", persona.obtenerOrganizaciones());
        return new ModelAndView(parametros, "trayectos/us_crear_trayecto.hbs");
    }

    public Response agregarTrayecto(Request request, Response response) {
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
        int idOrg = Integer.parseInt(request.queryParams("id_org"));
        Organizacion organizacion = this.repositorioDeOrganizaciones.buscar(idOrg);
        if (! persona.obtenerOrganizaciones().contains(organizacion)){
            response.redirect("/403");
            return response;
        }
        Trayecto trayecto = new Trayecto();
        trayecto.setPersona(persona);
        trayecto.setOrganizacion(organizacion);
        persona.getTrayectos().add(trayecto);
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(trayecto);
        EntityManagerHelper.getEntityManager().merge(persona);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        response.redirect("/user/trayectos");
        return response;
    }

    public Response eliminarTrayecto(Request request, Response response){
        int id = Integer.parseInt(request.params("idTrayecto"));
        this.repositorioDeTrayectos.eliminar(this.repositorioDeTrayectos.buscar(id));
        response.redirect("/user/trayectos");
        return response;
    }

    public ModelAndView mostrarTrayectos(Request request, Response response){
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("trayectos", persona.getTrayectos());
        return new ModelAndView(parametros, "trayectos/us_mis_trayectos.hbs");
    }

    // todo: review
    private Direccion cargarDireccion(Request request, String tipoDireccion){ //tipoDireccion = partida || destino
        String calle = request.queryParams(tipoDireccion + "-" + "calle");
        System.out.printf("calle " + calle);
        Integer altura = Integer.valueOf(request.queryParams(tipoDireccion + "-" + "altura"));
        System.out.printf("calle " + altura);
        Municipio municipio = new Municipio(); //request.params(tipoDireccion + "-" +
        System.out.printf("municiio " + altura);
        //Provincia provincia = new Provincia(municipio); request.params(tipoDireccion + "-" + "provincia");
        Localidad localidad = new Localidad(municipio , request.queryParams(tipoDireccion + "-" + "provincia"));

        return new Direccion(calle, altura, localidad);
    }

}

package controllers;

import domain.Direccion;
import domain.entidades.Localidad;
import domain.entidades.Municipio;
import domain.viaje.Tramo;
import domain.viaje.Trayecto;
import lombok.Setter;
import models.enMemoria.RepositorioDeOrganizacionesEnMemoria;
import models.enMemoria.RepositorioDeTramosEnMemoria;
import models.enMemoria.RepositorioDeTrayectosEnMemoria;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
public class TrayectosController {

    private RepositorioDeTramosEnMemoria repositorioDeTramos = new RepositorioDeTramosEnMemoria();
    private RepositorioDeTrayectosEnMemoria repositorioDeTrayectos = new RepositorioDeTrayectosEnMemoria();

    private RepositorioDeOrganizacionesEnMemoria repositorioDeOrganizaciones = new RepositorioDeOrganizacionesEnMemoria();

    public ModelAndView pantallaEditarTrayecto(Request request, Response response) {
        int idTrayecto = Integer.parseInt(request.params("idTrayecto"));
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tramos", this.repositorioDeTramos.buscarTodos().stream().map(Tramo::convertirADTO).collect(Collectors.toList()));
        parametros.put("idTrayecto", idTrayecto);
        parametros.put("organizaciones", this.repositorioDeOrganizaciones.buscarTodos());
        return new ModelAndView(parametros, "trayectos/us_editar_trayecto.hbs");
    }

    public Response editarTrayecto(Request request, Response response){
        int idTrayecto = Integer.parseInt(request.params("idTrayecto"));
        int idOrg = Integer.parseInt(request.queryParams("idOrg"));
        Trayecto trayecto = this.repositorioDeTrayectos.buscar(idTrayecto);
        trayecto.setOrganizacion(this.repositorioDeOrganizaciones.buscar(idOrg));
        System.out.println("EDITANDO TRAYECTO " + Integer.toString(idTrayecto));
        response.redirect("/user/trayectos");
        return response;
    }

    public Response agregarTrayecto(Request request, Response response) {
        Trayecto trayecto = new Trayecto(new ArrayList<>());
        this.repositorioDeTrayectos.guardar(trayecto);
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
        Map<String, Object> parametros = new HashMap<>();
        System.out.println();
        parametros.put("trayectos", this.repositorioDeTrayectos.buscarTodos());
        return new ModelAndView(parametros, "trayectos/us_mis_trayectos.hbs");
    }

    // todo: review
    private Direccion cargarDireccion(Request request, String tipoDireccion){ //tipoDireccion = partida || destino
        String calle = request.queryParams(tipoDireccion + "-" + "calle");
        System.out.printf("calle " + calle);
        Integer altura = Integer.valueOf(request.queryParams(tipoDireccion + "-" + "altura"));
        System.out.printf("calle " + altura);
        Municipio municipio = new Municipio(); //request.params(tipoDireccion + "-" +
        System.out.printf("municpio " + altura);
        //Provincia provincia = new Provincia(municipio); request.params(tipoDireccion + "-" + "provincia");
        Localidad localidad = new Localidad(municipio , request.queryParams(tipoDireccion + "-" + "provincia"));

        return new Direccion(calle, altura, localidad);
    }

}

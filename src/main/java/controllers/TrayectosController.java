package controllers;

import domain.Direccion;
import domain.entidades.Localidad;
import domain.entidades.Municipio;
import domain.viaje.privado.limpio.TramoLimpio;
import models.RepositorioDeTramosEnMemoria;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class TrayectosController {

    private RepositorioDeTramosEnMemoria repositorioDeTramos = new RepositorioDeTramosEnMemoria();

    public ModelAndView pantallaRegistrarTrayecto(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tramos", this.repositorioDeTramos.buscarTodos());
        return new ModelAndView(parametros, "trayectos/us_registrar_trayecto.hbs");
    }

    /*------------ Tramo Limpio ------------ */
    public ModelAndView pantallaRegistrarTramoLimpio(Request request, Response response) {
        return new ModelAndView(null, "trayectos/us_t_limpio.hbs");
    }

    public Response guardarTramoLimpio(Request request, Response response) {
        Direccion partida = this.cargarDireccion(request, "partida");

        Direccion destino = this.cargarDireccion(request, "destino");
        TramoLimpio nuevoTLimpio = new TramoLimpio("BICI", partida, destino); // todo: que era el tipo?
        this.repositorioDeTramos.guardar(nuevoTLimpio);

        response.redirect("/trayecto/registrar");
        return response;
    }

    public Response eliminarTramo(Request request, Response response){
        int id = Integer.parseInt(request.params("id"));
        this.repositorioDeTramos.eliminar(this.repositorioDeTramos.buscar(id));
        response.redirect("trayectos/registrar");
        return response;
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


    /*------------ Tramo Limpio ------------ */
    public ModelAndView pantallaRegistrarTramoPublico(Request request, Response response) {
        return new ModelAndView(null, "trayectos/us_t_publico.html");
    }
    public Response guardarTramoPublico(Request request, Response response) {
        Direccion partida = this.cargarDireccion(request, "partida");

        Direccion destino = this.cargarDireccion(request, "destino");
        //TramoContratado tramoContratado = new Contratado("tipo?", partida, destino); // todo: que era el tipo?

        //this.repositorioTramos.guardar(nuevoTLimpio); // guardarlo en el SQL

        response.redirect("/us_registrar_trayecto");
        return response;
    }

    /* ------- Tramo Contratado ----- */
    public ModelAndView pantallaRegistrarTramoContratado(Request request, Response response) {
        return new ModelAndView(null, "trayectos/us_t_contratado_crear.html");
    }


}

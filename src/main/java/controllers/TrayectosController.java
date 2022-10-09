package controllers;

import domain.Direccion;
import domain.entidades.Localidad;
import domain.entidades.Municipio;
import domain.entidades.Provincia;
import domain.viaje.privado.limpio.TLimpio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TrayectosController {

    public ModelAndView pantallaRegistrarTrayecto(Request request, Response response) {
        return new ModelAndView(null, "trayectos/us_registrar_trayecto.hbs");
    }

    /*------------ Tramo Limpio ------------ */
    public ModelAndView pantallaRegistrarTramoLimpio(Request request, Response response) {
        return new ModelAndView(null, "trayectos/us_t_limpio.hbs");
    }

    public Response guardarTramoLimpio(Request request, Response response) {
        Direccion partida = this.cargarDireccion(request, "partida");

        Direccion destino = this.cargarDireccion(request, "destino");
        TLimpio nuevoTLimpio = new TLimpio("tipo?", partida, destino); // todo: que era el tipo?

        //this.repositorioTramos.guardar(nuevoTLimpio); // guardarlo en el SQL

        response.redirect("/us_registrar_trayecto");
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
}

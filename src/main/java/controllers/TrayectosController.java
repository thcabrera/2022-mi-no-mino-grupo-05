package controllers;

import domain.Direccion;
import domain.entidades.Localidad;
import domain.entidades.Municipio;
import domain.viaje.privado.contratado.TramoContratado;
import domain.viaje.privado.limpio.TramoLimpio;
import domain.viaje.privado.particular.TipoParticular;
import domain.viaje.privado.particular.TramoParticular;
import domain.viaje.publico.TramoPublico;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrayectosController {

    public ModelAndView pantallaRegistrarTrayecto(Request request, Response response) {
        List<TramoLimpio> tramosLimpios = new ArrayList<>();
        Direccion origen = new Direccion("Baigorria",3057, new Localidad(1, null));
        Direccion destino = new Direccion("Campana",3500, new Localidad(1, null));
        TramoLimpio tramo = new TramoLimpio("BICI", origen, destino);
        TramoLimpio tramo2 = new TramoLimpio("A PATA", destino, origen);
        tramosLimpios.add(tramo);
        tramosLimpios.add(tramo2);
        List<TramoParticular> tramosParticulares = new ArrayList<>();
        TipoParticular autito = new TipoParticular(0.0);
        autito.setDescripcion("Autito");
        tramosParticulares.add(new TramoParticular(null, autito, origen,destino, false));
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tramosLimpios", tramosLimpios);
        parametros.put("tramosParticulares", tramosParticulares);
        parametros.put("tramosPublicos", new ArrayList<TramoPublico>());
        parametros.put("tramosContratados", new ArrayList<TramoContratado>());
        return new ModelAndView(parametros, "trayectos/us_registrar_trayecto.hbs");
    }

    /*------------ Tramo Limpio ------------ */
    public ModelAndView pantallaRegistrarTramoLimpio(Request request, Response response) {
        return new ModelAndView(null, "trayectos/us_t_limpio.hbs");
    }

    public Response guardarTramoLimpio(Request request, Response response) {
        Direccion partida = this.cargarDireccion(request, "partida");

        Direccion destino = this.cargarDireccion(request, "destino");
        TramoLimpio nuevoTLimpio = new TramoLimpio("tipo?", partida, destino); // todo: que era el tipo?

        //this.repositorioTramos.guardar(nuevoTLimpio); // guardarlo en el SQL

        response.redirect("/trayecto/registrar");
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

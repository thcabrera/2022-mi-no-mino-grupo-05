package controllers;

import domain.Direccion;
import domain.entidades.Localidad;
import domain.entidades.Municipio;
import domain.viaje.Tramo;
import domain.viaje.privado.limpio.TramoLimpio;
import lombok.Setter;
import models.RepositorioDeTramosEnMemoria;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

@Setter
public class TramosController {

    private RepositorioDeTramosEnMemoria repositorioDeTramos = new RepositorioDeTramosEnMemoria();

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
        int id = Integer.parseInt(request.params("idTramo"));
        this.repositorioDeTramos.eliminar(this.repositorioDeTramos.buscar(id));
        System.out.printf("Borrando id %d!\n",id);
        response.redirect("/user/trayectos/editar/" + request.params("idTrayecto"));
        return response;
    }

    public ModelAndView pantallaEditarTramoLimpio(Request request, Response response){
        int idTrayecto = Integer.parseInt(request.params("idTrayecto"));
        int idTramo = Integer.parseInt(request.params("idTramo"));
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idTrayecto",idTrayecto);
        return new ModelAndView(parametros, "trayectos/us_t_limpio.hbs");
    }

    public Response editarTramoLimpio(Request request, Response response){
        int idTramo = Integer.parseInt(request.params("idTramo"));
        Tramo tramo = this.repositorioDeTramos.buscar(idTramo);
        System.out.println("Editando tramo limpio!");
        response.redirect("/user/trayectos/" + request.params("idTrayecto"));
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
        //TramoContratado tramoContratado = new Contratado("tipo?", partida, destino);

        //this.repositorioTramos.guardar(nuevoTLimpio); // guardarlo en el SQL

        response.redirect("/us_registrar_trayecto");
        return response;
    }

    /* ------- Tramo Contratado ----- */
    public ModelAndView pantallaRegistrarTramoContratado(Request request, Response response) {
        return new ModelAndView(null, "trayectos/us_t_contratado_crear.html");
    }


}

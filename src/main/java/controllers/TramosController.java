package controllers;

import domain.Direccion;
import domain.entidades.Localidad;
import domain.entidades.Municipio;
import domain.entidades.Provincia;
import domain.viaje.Tramo;
import domain.viaje.privado.limpio.TramoLimpio;
import lombok.Setter;
import models.RepositorioDeLocalidades;
import models.RepositorioDeMunicipios;
import models.RepositorioDeProvincias;
import models.enMemoria.RepositorioDeTramosEnMemoria;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
public class TramosController {

    private RepositorioDeTramosEnMemoria repositorioDeTramos = new RepositorioDeTramosEnMemoria();
    private RepositorioDeProvincias repositorioDeProvincias = new RepositorioDeProvincias();
    private RepositorioDeMunicipios repositorioDeMunicipios = new RepositorioDeMunicipios();
    private RepositorioDeLocalidades repositorioDeLocalidades = new RepositorioDeLocalidades();

    /*------------ Tramo Limpio ------------ */
    public ModelAndView pantallaRegistrarTramoLimpio(Request request, Response response) {
        int idTrayecto = Integer.parseInt(request.params("idTrayecto"));
        System.out.println(idTrayecto);
        Map<String, Object> parametros = new HashMap<>();
        List<Provincia> provincias = this.repositorioDeProvincias.buscarTodos();
//        List<Provincia.ProvinciaDTO> dtoProvincias = provincias.stream().map(Provincia::convertirADTO).collect(Collectors.toList());
        parametros.put("provincias", provincias);
        parametros.put("idTrayecto", idTrayecto);
        return new ModelAndView(parametros, "trayectos/us_t_limpio.hbs");
    }

    public Response guardarTramoLimpio(Request request, Response response) {

        String tipo = request.queryParams("tipo");
        Direccion partida = this.cargarDireccion(request, "partida");
        Direccion destino = this.cargarDireccion(request, "destino");

        TramoLimpio nuevoTLimpio = new TramoLimpio(tipo, partida, destino);

        this.repositorioDeTramos.guardar(nuevoTLimpio);

        response.redirect("/user/trayectos/editar/" + request.params("idTrayecto"));
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
        response.redirect("/user/trayectos" + request.params("idTrayecto"));
        return response;
    }


    private Direccion cargarDireccion(Request request, String tipoDireccion){ //tipoDireccion = partida || destino
        String calle = request.queryParams(tipoDireccion + "-" + "calle");
        int altura = Integer.parseInt(request.queryParams(tipoDireccion + "-" + "altura"));
        int idMunicipio = Integer.parseInt(request.queryParams(tipoDireccion + "-" + "municipio"));
        Municipio municipio = this.repositorioDeMunicipios.buscar(idMunicipio);
        Localidad localidad = null;
        String idLocalidad = request.queryParams(tipoDireccion + "-" + "localidad");
        if (idLocalidad != null)
            localidad = this.repositorioDeLocalidades.buscar(Integer.parseInt(idLocalidad));
        return new Direccion(calle, altura, localidad, municipio, municipio.getProvincia());
    }


    /*------------ Tramo Limpio ------------ */
    public ModelAndView pantallaRegistrarTramoPublico(Request request, Response response) {
        return new ModelAndView(null, "trayectos/us_t_publico.hbs");
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

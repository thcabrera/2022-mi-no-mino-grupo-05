package controllers;

import domain.Direccion;
import domain.db.EntityManagerHelper;
import domain.entidades.*;
import domain.viaje.Tramo;
import domain.viaje.Trayecto;
import domain.viaje.privado.contratado.Servicio;
import domain.viaje.privado.contratado.TramoContratado;
import domain.viaje.privado.limpio.TramoLimpio;
import domain.viaje.privado.particular.Combustible;
import domain.viaje.privado.particular.TipoParticular;
import domain.viaje.privado.particular.TramoParticular;
import domain.viaje.publico.TipoLinea;
import domain.viaje.publico.TramoPublico;
import helpers.UsuarioHelper;
import lombok.Setter;
import repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
public class TramosController {

    private RepositorioDeTramos repositorioDeTramos   = new RepositorioDeTramos();
    private RepositorioDeProvincias repositorioDeProvincias    = new RepositorioDeProvincias();
    private RepositorioDeMunicipios repositorioDeMunicipios    = new RepositorioDeMunicipios();
    private RepositorioDeLocalidades repositorioDeLocalidades  = new RepositorioDeLocalidades();
    private RepositorioDeTrayectos repositorioDeTrayectos = new RepositorioDeTrayectos();

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
        String calle = request.queryParams("calle" + "-" + tipoDireccion);
        int altura = Integer.parseInt(request.queryParams("altura" + "-" + tipoDireccion));
        int idMunicipio = Integer.parseInt(request.queryParams("municipio" + "-" + tipoDireccion));
        Municipio municipio = this.repositorioDeMunicipios.buscar(idMunicipio);
        Localidad localidad = null;
        String idLocalidad = request.queryParams("localidad" + "-" + tipoDireccion);
        if (idLocalidad != null)
            localidad = this.repositorioDeLocalidades.buscar(Integer.parseInt(idLocalidad));
        return new Direccion(calle, altura, localidad, municipio, municipio.getProvincia());
    }


    /*------------ Tramo Limpio ------------ */
    public ModelAndView pantallaRegistrarTramoPublico(Request request, Response response) {
        List<TipoLinea> todos = EntityManagerHelper.getEntityManager()
                .createQuery("from " + TipoLinea.class.getName())
                .getResultList();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("tipo_transporte", todos);
        }}, "trayectos/us_t_publico.hbs");
    }

    public Response guardarTramoPublico(Request request, Response response) {
       // Direccion partida = this.cargarDireccion(request, "partida");

      //  Direccion destino = this.cargarDireccion(request, "destino");
        //TramoContratado tramoContratado = new Contratado("tipo?", partida, destino);

        //this.repositorioTramos.guardar(nuevoTLimpio); // guardarlo en el SQL

        //response.redirect("/user/trayectos");
        response.redirect("user/trayectos/editar");

        return response;
    }

    /* ------- Tramo Contratado ----- */
    public ModelAndView pantallaRegistrarTramoContratado(Request request, Response response) {
        List<Provincia.ProvinciaDTO> provincias = this.repositorioDeProvincias.buscarTodos()
                .stream().map(Provincia::convertirADTO)
                .collect(Collectors.toList())
                .stream().sorted(Comparator.comparing(Provincia.ProvinciaDTO::getDescripcion))
                .collect(Collectors.toList());
        List<Servicio> servicios = EntityManagerHelper.getEntityManager()
                .createQuery("from " + Servicio.class.getName())
                .getResultList();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("tiposServicio", servicios);
            put("provincias", provincias);
            put("idTrayecto", request.params("idTrayecto"));
        }}, "trayectos/us_tramo_contratado_crear.hbs");
    }

    public ModelAndView editarTramoContratado(Request request, Response response){
        try{
            TramoContratado tramo = (TramoContratado) this.repositorioDeTramos.buscar(Integer.parseInt(request.params("idTramo")));
            List<Provincia.ProvinciaDTO> provincias = this.repositorioDeProvincias.buscarTodos()
                    .stream().map(Provincia::convertirADTO)
                    .collect(Collectors.toList())
                    .stream().sorted(Comparator.comparing(Provincia.ProvinciaDTO::getDescripcion))
                    .collect(Collectors.toList());
            List<Servicio> servicios = EntityManagerHelper.getEntityManager()
                    .createQuery("from " + Servicio.class.getName())
                    .getResultList();
            return new ModelAndView(new HashMap<String, Object>(){{
                put("tiposServicio", servicios);
                put("provincias", provincias);
                put("idTrayecto", request.params("idTrayecto"));
                put("tramo", tramo);
            }}, "trayectos/us_tramo_contratado_crear.hbs");
        } catch(IllegalArgumentException e){
            response.redirect("/404");
        }
        return null;
    }

    public Response guardarTramoContratado(Request request, Response response){
        try{
            String idTrayecto = request.params("idTrayecto");
            Trayecto trayecto = this.repositorioDeTrayectos.buscar(Integer.parseInt(idTrayecto));
            // SI LA PERSONA QUE HIZO LA REQUEST NO ES LA DUEÑA DEL TRAYECTO
            if (! UsuarioHelper.usuarioLogueado(request).getActor().equals(trayecto.getPersona())){
                response.redirect("/403");
                return response;
            }
            Servicio servicio = EntityManagerHelper.getEntityManager()
                    .find(Servicio.class, Integer.parseInt(request.queryParams("tipo_servicio")));
            Direccion direccionInicio = cargarDireccion(request, "partida");
            Direccion direccionFin = cargarDireccion(request, "destino");
            boolean esCompartido = "true".equals(request.queryParams("es_compartido"));
            Tramo tramo = new TramoContratado(servicio, direccionInicio, direccionFin, esCompartido);
            trayecto.agregarTramos(tramo);
            repositorioDeTrayectos.modificar(trayecto);
            response.redirect("/user/trayectos/editar/" + idTrayecto);
        } catch(IllegalArgumentException e){
            System.out.println("Entré al catch!");
            response.redirect("/404");
        }
        return response;
    }

    public Response modificarTramoContratado(Request request, Response response){
        try{
            Tramo tramo = (TramoContratado) this.repositorioDeTramos.buscar(Integer.parseInt(request.params("idTramo")));
            System.out.println("EDITANDO TRAMO CONTRATADO!");
            response.redirect("/user/trayectos/editar/"
                    + request.params("idTrayecto"));
            return response;
        } catch(IllegalArgumentException e){
            response.redirect("/404");
        }
        return null;
    }

    /* ------- Tramo Contratado ----- */
    public ModelAndView pantallaRegistrarTramoParticular(Request request, Response response) {
        List<Provincia.ProvinciaDTO> provincias = this.repositorioDeProvincias.buscarTodos()
                .stream().map(Provincia::convertirADTO)
                .collect(Collectors.toList())
                .stream().sorted(Comparator.comparing(Provincia.ProvinciaDTO::getDescripcion))
                .collect(Collectors.toList());
        List<Combustible> combustibles = EntityManagerHelper.getEntityManager()
                .createQuery("from " + Combustible.class.getName())
                .getResultList();
        List<TipoParticular> vehiculos = EntityManagerHelper.getEntityManager()
                .createQuery("from " + TipoParticular.class.getName())
                .getResultList();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("tiposCombustible", combustibles);
            put("tiposVehiculo", vehiculos);
            put("provincias", provincias);
            put("idTrayecto", request.params("idTrayecto"));
        }}, "trayectos/us_tramo_particular_crear.hbs");
    }

    public Response editarTramo(Request request, Response response){
        int idTramo = Integer.parseInt(request.params("idTramo"));
        int idTrayecto = Integer.parseInt(request.params("idTrayecto"));
        // TODO verificar que el tramo pertenezca al trayecto y el trayecto al user
        Tramo tramo = this.repositorioDeTramos.buscar(idTramo);
        if (tramo instanceof TramoContratado)
            response.redirect("/user/trayectos/tramo/contratado/editar/"
                    + request.params("idTrayecto") + "/"
                    + request.params("idTramo"));
        else if (tramo instanceof TramoLimpio)
            response.redirect("/user/trayectos/tramo/limpio/editar/"
                    + request.params("idTrayecto") + "/"
                    + request.params("idTramo"));
        else if (tramo instanceof TramoPublico)
            response.redirect("/user/trayectos/tramo/publico/editar/"
                    + request.params("idTrayecto") + "/"
                    + request.params("idTramo"));
        else if (tramo instanceof TramoParticular)
            response.redirect("/user/trayectos/tramo/particular/editar/"
                    + request.params("idTrayecto") + "/"
                    + request.params("idTramo"));
        else{
            response.redirect("/404");
        }
        return response;
    }
}

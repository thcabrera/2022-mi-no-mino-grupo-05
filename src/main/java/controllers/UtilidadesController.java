package controllers;

import domain.entidades.*;
import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;
import repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UtilidadesController {

    private RepositorioDeMunicipios repositorioDeMunicipios = new RepositorioDeMunicipios();
    private RepositorioDeProvincias repositorioDeProvincias = new RepositorioDeProvincias();
    private RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    private RepositorioLineas repositorioLineas = new RepositorioLineas();
    private RepositorioParadas reposotirioParadas = new RepositorioParadas();

    public List<Parada.ParadaDTO> obtenerParadasDestino(Request request, Response response){
    //System.out.println(request.params("id_linea"));
        int idLinea = Integer.parseInt(request.params("id_linea"));
        int id_paradaPartida = Integer.parseInt(request.params("id_paradaPartida"));
        List<Parada> paradas = this.reposotirioParadas.paradasPorLineaParada(idLinea, id_paradaPartida);
        if (paradas == null)
            return new ArrayList<>();
        return paradas.stream().map(Parada::convertirADTO).collect(Collectors.toList());


    }

    public List<Parada.ParadaDTO> obtenerParadas(Request request, Response response){
        int idLinea = Integer.parseInt(request.params("id_linea"));
        List<Parada> paradas = this.reposotirioParadas.paradasPorLinea(idLinea);
        if (paradas == null)
            return new ArrayList<>();
        return paradas.stream().map(Parada::convertirADTO).collect(Collectors.toList());

    }

    public List<Linea.LineaDTO> obtenerLineas(Request request, Response response){
        int idTipoLinea = Integer.parseInt(request.params("idTipoTransporte"));
        List<Linea> lineas = this.repositorioLineas.lineasPorTipo(idTipoLinea);
        if (lineas == null)
            return new ArrayList<>();
       return lineas.stream().map(Linea::convertirADTO).collect(Collectors.toList());
    }

    public List<Municipio.MunicipioDTO> obtenerMunicipios(Request request, Response response){
        int idProvincia = Integer.parseInt(request.params("idProvincia"));
        Provincia provincia = repositorioDeProvincias.buscar(idProvincia);
        if (provincia == null)
            return new ArrayList<>();
        List<Municipio> municipios = new ArrayList<>(provincia.getMunicipios());
        return municipios.stream().map(Municipio::convertirADTO).collect(Collectors.toList());
    }

    public List<Localidad.LocalidadDTO> obtenerLocalidades(Request request, Response response){
        int idMunicipio = Integer.parseInt(request.params("idMunicipio"));
        Municipio municipio = repositorioDeMunicipios.buscar(idMunicipio);
        if (municipio == null)
            return new ArrayList<>();
        List<Localidad> localidades = new ArrayList<>(municipio.getLocalidades());
        return localidades.stream().map(Localidad::convertirADTO).collect(Collectors.toList());
    }

    // IMPORTANTE: es necesario pasar el DTO del Area, ya que si pasamos el area así de una
    // el transformador a JSON caería en un loop infinito y saltaría un StackOverflowException
    // por que pasaría esto? Porque el area tiene una org, entonces también haria el JSON -->
    // --> de la organización. Esta a su vez tiene la lista de areas, por lo que volvería -->
    // --> a hacer el JSON de la misma área y asi infinitamente
    public List<Area.AreaDTO> obtenerAreas(Request request, Response response){
        int idOrganizacion = Integer.parseInt(request.params("idOrganizacion"));
        Organizacion organizacion = repositorioDeOrganizaciones.buscar(idOrganizacion);
        if (organizacion == null)
            return new ArrayList<>();
        return organizacion.getAreas().stream().map(Area::convertirADTO).collect(Collectors.toList());
    }

/*    public List<Area.AreaDTO> obtenerLineas(Request request, Response response){
        int idOrganizacion = Integer.parseInt(request.params("idOrganizacion"));
        Organizacion organizacion = repositorioDeOrganizaciones.buscar(idOrganizacion);
        if (organizacion == null)
            return new ArrayList<>();
        return organizacion.getAreas().stream().map(Area::convertirADTO).collect(Collectors.toList());
    }*/



    public ModelAndView pantallaClientePerdido(Request request, Response response) {
        response.status(404);
        return new ModelAndView(null, "/utilidades/perdido.hbs");
    }

    public ModelAndView pantallaAccesoDenegado(Request request, Response response) {
        response.status(403);
        return new ModelAndView(null, "/utilidades/acceso_denegado.hbs");
    }


}

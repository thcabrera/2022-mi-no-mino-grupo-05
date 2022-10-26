package controllers;

import domain.entidades.*;
import models.RepositorioDeMunicipios;
import models.RepositorioDeOrganizaciones;
import models.RepositorioDeProvincias;
import spark.Request;
import spark.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UtilidadesController {

    private RepositorioDeMunicipios repositorioDeMunicipios = new RepositorioDeMunicipios();
    private RepositorioDeProvincias repositorioDeProvincias = new RepositorioDeProvincias();
    private RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();

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

}
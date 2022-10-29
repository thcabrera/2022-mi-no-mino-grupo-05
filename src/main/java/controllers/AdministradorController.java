package controllers;

import domain.Direccion;
import domain.db.EntityManagerHelper;
import domain.entidades.*;
import repositorios.RepositorioDeOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdministradorController {

    private RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();

    public ModelAndView pantallaPrincipal(Request request, Response response){
        return new ModelAndView(null, "admin/admin_principal.hbs");
    }

    public ModelAndView pantallaVerOrganizaciones(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();

        List<Organizacion> organizaciones = repositorioDeOrganizaciones
                .buscarTodos()
                .stream().sorted(Comparator.comparing(Organizacion::getNombre))
                .collect(Collectors.toList());
        parametros.put("organizaciones", organizaciones);

        // agregamos los mensajes (si los hay)

        String msg = request.queryParams("msg");
        if ("deleted".equals(msg))
            parametros.put("organizacionEliminada",true);
        else if ("created".equals(msg))
            parametros.put("organizacionCreada",true);
        return new ModelAndView(parametros, "admin/admin_ver_organizaciones.hbs");
    }

    public ModelAndView pantallaAgregarOrganizacion(Request request, Response response){
        List<Clasificacion> clasificaciones = EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Clasificacion.class.getName())
                .getResultList();
        List<TipoOrg> tipos = EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + TipoOrg.class.getName())
                .getResultList();
        List<Provincia> provincias = EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Provincia.class.getName())
                .getResultList();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("clasificaciones", clasificaciones);
        parametros.put("tipos", tipos);
        parametros.put("provincias", provincias);
        return new ModelAndView(parametros, "admin/admin_agregar_organizacion.hbs");
    }

    public Direccion cargarDireccion(Request request){
        Localidad localidad;
        if (request.queryParams("localidad") != null)
            localidad = EntityManagerHelper.getEntityManager()
                .find(Localidad.class, Integer.parseInt(request.queryParams("localidad")));
        else localidad = null;
        return new Direccion(
                request.queryParams("calle"),
                Integer.parseInt(request.queryParams("altura")),
                localidad,
                EntityManagerHelper.getEntityManager()
                        .find(Municipio.class, Integer.parseInt(request.queryParams("municipio"))),
                EntityManagerHelper.getEntityManager()
                        .find(Provincia.class, Integer.parseInt(request.queryParams("provincia")))
                );
    }

    public Response agregarOrganizacion(Request request, Response response){
        try{
            String nombre = request.queryParams("nombre");
            String razonSocial = request.queryParams("razon_social");
            int idClasificacion = Integer.parseInt(request.queryParams("clasificacion"));
            int idTipoOrg = Integer.parseInt(request.queryParams("tipo"));
            Clasificacion clasificacion = EntityManagerHelper
                    .getEntityManager()
                    .find(Clasificacion.class, idClasificacion);
            TipoOrg tipo = EntityManagerHelper
                    .getEntityManager()
                    .find(TipoOrg.class, idTipoOrg);
            if (clasificacion == null || tipo == null)
                response.redirect("/400");
            Organizacion organizacion = new Organizacion(
                    nombre,
                    razonSocial,
                    tipo,
                    cargarDireccion(request),
                    clasificacion
            );
            repositorioDeOrganizaciones.guardar(organizacion);
            response.redirect("/administrador/organizaciones?msg=created");
            return response;
        } catch(NumberFormatException e){
            response.redirect("/404");
        }
        return response;
    }

    public Response eliminarOrganizacion(Request request, Response response){
        try {
            int idOrg = Integer.parseInt(request.params("idOrg"));
            repositorioDeOrganizaciones.eliminar(repositorioDeOrganizaciones.buscar(idOrg));
            EntityManagerHelper.closeEntityManager();
            response.redirect("/administrador/organizaciones?msg=deleted");
        } catch(NumberFormatException e){
            // si el params que nos pasaron no es un número
            response.redirect("/404");
        } catch(IllegalArgumentException e){
            // si el params que nos pasaron no corresponde a un id de una org
            response.redirect("/404");
        }
        return response;
    }

}

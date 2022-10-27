package controllers;

import domain.db.EntityManagerHelper;
import domain.entidades.Municipio;
import domain.entidades.Solicitud;
import models.RepositorioDeSolicitudes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolicitudesController {

    private RepositorioDeSolicitudes repositorioDeSolicitudes = new RepositorioDeSolicitudes();

    public ModelAndView mostrarTodasParaOrg(Request request, Response response) {
        int idOrganizacion = Integer.parseInt(request.params("idOrg"));
        Map<String, Object> parametros = new HashMap<>();
        System.out.println("el id de la org es:" + idOrganizacion);
        List<Solicitud> solicitudes =  this.repositorioDeSolicitudes.buscarTodosParaOrg(idOrganizacion);
        if(solicitudes == null){
            System.out.println("solicitudes == null"+ true + "=> sols:" +  solicitudes);
            return new ModelAndView(null, "org/org_aceptar_miembro.hbs");
        }else {
            parametros.put("solicitudes",solicitudes);
            return new ModelAndView(parametros, "org/org_aceptar_miembro.hbs");
        }
    }

    public Response eliminar(Request request, Response response) {
        int idOrganizacion = Integer.parseInt(request.params("idOrg"));
        int idSolicitud = Integer.parseInt(request.params("idSol"));
        this.repositorioDeSolicitudes.eliminar(this.repositorioDeSolicitudes.buscar(idSolicitud));
        System.out.printf("Borrando id %d!\n", idSolicitud);
        response.redirect("/org/solicitudes" + request.params("idOrganizacion"));
        return response;

    }

}

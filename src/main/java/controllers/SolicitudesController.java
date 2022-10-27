package controllers;

import domain.db.EntityManagerHelper;
import domain.entidades.Area;
import domain.entidades.Municipio;
import domain.entidades.Persona;
import domain.entidades.Solicitud;
import models.RepositorioDeAreas;
import models.RepositorioDeSolicitudes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolicitudesController {

    private RepositorioDeSolicitudes repositorioDeSolicitudes = new RepositorioDeSolicitudes();
    private RepositorioDeAreas respositorioDeAreas = new RepositorioDeAreas();

    public ModelAndView mostrarTodasParaOrg(Request request, Response response) {
        int idOrganizacion = Integer.parseInt(request.params("idOrg"));
        Map<String, Object> parametros = new HashMap<>();
        //System.out.println("el id de la org es:" + idOrganizacion);
        List<Solicitud> solicitudes =  this.repositorioDeSolicitudes.buscarTodosParaOrg(idOrganizacion);
        if(solicitudes == null){
            parametros.put("solicitudes", new ArrayList<Solicitud>());
            return new ModelAndView(parametros, "org/org_aceptar_miembro.hbs");
        }else {
            parametros.put("solicitudes",solicitudes);
            return new ModelAndView(parametros, "org/org_aceptar_miembro.hbs");
        }
    }

    public Response eliminar(Request request, Response response) {
        int idSolicitud = Integer.parseInt(request.params("idSol"));
        this.repositorioDeSolicitudes.eliminar(this.repositorioDeSolicitudes.buscar(idSolicitud));
        System.out.printf("Borrando id %d!\n", idSolicitud);
        response.redirect("/org/solicitudes" + request.params("idOrganizacion"));
        return response;

    }

    public Response aceptar(Request request, Response response) {
        System.out.println("entre a acetpar");
        int idSolicitud = Integer.parseInt(request.params("idSol"));
        Solicitud solicitud = this.repositorioDeSolicitudes.buscar(idSolicitud);
        Area area = solicitud.getAreaSolicitada();
        Persona solicitante = solicitud.getSolicitante();
        //agrego area a la persona
        solicitante.agregarArea(area);

        //agrego persona al area
        area.agregarMiembro(solicitante);

        // merge
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(area);
        EntityManagerHelper.getEntityManager().getTransaction().commit();


        this.repositorioDeSolicitudes.eliminar(solicitud);
        response.redirect("/org/solicitudes" + request.params("idOrganizacion"));

        return response;

    }

}

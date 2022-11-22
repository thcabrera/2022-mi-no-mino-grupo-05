package controllers;

import domain.entidades.Organizacion;
import helpers.UsuarioHelper;
import repositorios.RepositorioDeReportes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class ReportesController {
    private RepositorioDeReportes repositorioDeReporte = new RepositorioDeReportes();

    public ModelAndView mostrarReportesUsuario(Request request, Response response) {
//        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
//
//        Map<String, Object> parametros = new HashMap<>();
//        System.out.println();
//        parametros.put("reportes", this.repositorioDeReporte.buscarTodosEjemplo()); // todo: lean
//        return new ModelAndView(parametros, "user/us_reportes.hbs");
        return null;
    }

    /* ----------- ORganizacion ----------------*/


    public ModelAndView mostrarReportesOrganizacion(Request request, Response response) {
        Organizacion organizacion = (Organizacion) UsuarioHelper.usuarioLogueado(request).getActor();


        //Periodicidad periodo =
        Map<String, Object>  parametros = cargarPeriodicidades();

        return new ModelAndView(parametros, "org/org_reportes.hbs");
    }

    public ModelAndView mostrarReportesOrganizacionConPeriodicidad(Request request, Response response) {
//        Organizacion organizacion = (Organizacion) UsuarioHelper.usuarioLogueado(request).getActor();
//        int idPeriodicidad = Integer.parseInt(request.params("idPeriodicidad"));
//
//
//        Periodicidad periodicidad = this.buscarPeriodicidad(idPeriodicidad);
//        System.out.printf( " periodicidad = "+ periodicidad);
//
//        Map<String, Object>  parametros = cargarPeriodicidades();
//        parametros.put("organizacion", organizacion);
//        parametros.put("hcActividades", organizacion.calculoHCActividades(periodicidad));
//        parametros.put("hcTrayectos", organizacion.calculoHCTrayectos());
//        parametros.put("hcTotal", organizacion.calculoHC(periodicidad));
//
//        parametros.put("areas", organizacion.getAreas().stream().map(Area::convertirAReporteAreaDTO).collect(Collectors.toList()));
//
//        return new ModelAndView(parametros, "org/org_reportes.hbs");
        return null;
    }

    private HashMap<String, Object> cargarPeriodicidades() {
//        HashMap<String, Object> periodicidades = new HashMap<>();
//
//
//        System.out.println("entro a reportes");
//        List<Anual> anuales =  this.repositorioDePeriodicidad.buscarTodosAnuales();
//
//        if(anuales.size() == 0 || mensuales.size() == 0 ){
//            periodicidades.put("periodicidades", new ArrayList<Periodicidad>());
//            System.out.printf("no hay periodicidadse");
//        }else {
//            periodicidades.put("anuales", anuales);
//            periodicidades.put("mensuales", mensuales);
//
//            System.out.printf("hay anuales" + anuales.toString());
//            System.out.printf("hay mensuales" + mensuales.toString());
//        }
        return null;
    }
}
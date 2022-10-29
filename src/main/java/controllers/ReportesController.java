package controllers;

import domain.entidades.Area;
import domain.entidades.Organizacion;
import domain.entidades.Persona;
import domain.mediciones.consumos.Anual;
import domain.mediciones.consumos.Mensual;
import domain.mediciones.consumos.Periodicidad;
import helpers.OrganizacionHelper;
import helpers.UsuarioHelper;
import repositorios.RepositorioDePeriodicidad;
import repositorios.RepositorioDeReportes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportesController {
    private RepositorioDeReportes repositorioDeReporte = new RepositorioDeReportes();
    private RepositorioDePeriodicidad repositorioDePeriodicidad = new RepositorioDePeriodicidad();

    public ModelAndView mostrarReportesUsuario(Request request, Response response) {
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();

        Map<String, Object> parametros = new HashMap<>();
        System.out.println();
        parametros.put("reportes", this.repositorioDeReporte.buscarTodosEjemplo()); // todo: lean
        return new ModelAndView(parametros, "user/us_reportes.hbs");
    }

    /* ----------- ORganizacion ----------------*/
    public ModelAndView mostrarReportesOrganizacion(Request request, Response response) {
        Organizacion organizacion = (Organizacion) UsuarioHelper.usuarioLogueado(request).getActor();

        //Periodicidad periodo =
        Map<String, Object> parametros = new HashMap<>();
        System.out.println("entro a reportes");
        List<Anual> anuales =  this.repositorioDePeriodicidad.buscarTodosAnuales();
        List<Mensual> mensuales =  this.repositorioDePeriodicidad.buscarTodosMensuales();

        if(anuales.size() == 0 || mensuales.size() == 0 ){
            parametros.put("periodicidades", new ArrayList<Periodicidad>());
            System.out.printf("no hay periodicidadse");
        }else {
            parametros.put("anuales", anuales);
            parametros.put("mensuales", mensuales);

            System.out.printf("hay anuales" + anuales.toString());
            System.out.printf("hay mensuales" + mensuales.toString());
        }
        return new ModelAndView(parametros, "org/org_reportes.hbs");
    }

    public ModelAndView mostrarReportesOrganizacionConPeriodicidad(Request request, Response response) {
        Organizacion organizacion = (Organizacion) UsuarioHelper.usuarioLogueado(request).getActor();
        int idPeriodicidad = Integer.parseInt(request.params("idPeriodicidad"));


        Periodicidad periodicidad = this.buscarPeriodicidad(idPeriodicidad);
        System.out.printf( " periodicidad = "+ periodicidad);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("organizacion", organizacion);
        parametros.put("hcActividades", organizacion.calculoHCActividades(periodicidad));
        parametros.put("hcTrayectos", organizacion.calculoHCTrayectos());
        parametros.put("hcTotal", organizacion.calculoHC(periodicidad));
        parametros.put("areas", organizacion.getAreas());
        
        return new ModelAndView(parametros, "org/org_reportes.hbs");
    }

    private Periodicidad buscarPeriodicidad(Integer id){
        Anual anual =  this.repositorioDePeriodicidad.buscarAnual(id);
        if(anual != null){
            System.out.println("soy anual");
            return anual;
        }else{
            System.out.println("soy mensual??");

            return  this.repositorioDePeriodicidad.buscarMensual(id);
        }
    }
}
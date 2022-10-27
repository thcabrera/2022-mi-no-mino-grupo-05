package controllers;

import models.RepositorioDeAreas;
import models.RepositorioDeReportes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ReportesController {
    private RepositorioDeReportes repositorioDeReporte = new RepositorioDeReportes();

    public ModelAndView mostrarReportes(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        System.out.println();
        parametros.put("huellaDeCarbono", this.repositorioDeReporte.buscarTodos());
        return new ModelAndView(parametros, "user/us_reportes.hbs");
    }
}
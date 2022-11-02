package repositorios;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RecomendacionesController {
    public ModelAndView mostrarRecomendaciones(Request request, Response response) {
        return new ModelAndView(null, "recomendaciones/recomendaciones.hbs");
    }
}

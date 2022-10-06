package controllers;

import domain.entidades.Organizacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class OrganizacionesController {
    //ABM: A->Alta |B-> Baja |M-> Modificaco  |L->Listado | V->Visualizar

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");

        Organizacion organizacion = db.EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, new Integer(idBuscado));
        return new ModelAndView(new HashMap<String, Object>() {{
            put("servicio", idBuscado);

        }}, "organizaciones/organziacion.hbs");

    }


}

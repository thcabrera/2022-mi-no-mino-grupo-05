package controllers;

import com.sun.org.apache.xpath.internal.operations.Or;
import domain.entidades.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import domain.db.EntityManagerHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizacionesController {
    //ABM: A->Alta |B-> Baja |M-> Modificaco  |L->Listado | V->Visualizar

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");

        Organizacion organizacion = EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, new Integer(idBuscado));
        return new ModelAndView(new HashMap<String, Object>() {{
            put("servicio", idBuscado);

        }}, "organizaciones/organizacion.hbs");

    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        //List<Organizacion> usuarios = this.repo.buscarTodos();

        List<Area> areasEjemplo = this.getListaOrgsEjemplo();

        parametros.put("areas", areasEjemplo);
        //asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "user/us_organizaciones.hbs");
    }



    /* hardcodeo para no levantar workbench*/
    private List<Area> getListaOrgsEjemplo() {
        List<Organizacion> orgs = new ArrayList<>();
        Organizacion lennyDespe = new Organizacion("SA", new TipoOrg("empresa") , null, null);
        Area sistemas = new Area("Sistemas", lennyDespe);
        Area ejecutivo = new Area("Ejecutivo", lennyDespe);

        Organizacion titoUtn = new Organizacion("UNI", new TipoOrg("educativa"), null, null);

        Area coaching = new Area("Coaching", titoUtn);
        orgs.add(lennyDespe);
        orgs.add(titoUtn);

        Persona lenny = new Persona("lean", "lienard",43814111, Documentacion.DNI);
        Persona tito = new Persona("tito", "lecaldare",43812754, Documentacion.DNI);


        lenny.solicitarAlta(lennyDespe, ejecutivo);
        lennyDespe.aceptarEmpleado(lenny, ejecutivo);

        lenny.solicitarAlta(lennyDespe, sistemas);
        lennyDespe.aceptarEmpleado(lenny, sistemas);

        tito.solicitarAlta(titoUtn, coaching);
        titoUtn.aceptarEmpleado(tito, coaching);

        System.out.println("/n areas de lenny "+ lenny.getListaAreas().get(0).getNombre());
        return lenny.getListaAreas();
    }



}

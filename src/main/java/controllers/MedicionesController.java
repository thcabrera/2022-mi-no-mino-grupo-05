package controllers;

import domain.db.EntityManagerHelper;
import domain.entidades.Organizacion;
import domain.entidades.Persona;
import domain.mediciones.consumos.actividades.Actividad;
import domain.mediciones.consumos.actividades.ActividadConsumo;
import domain.mediciones.consumos.actividades.Logistica;
import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.importador.ImportarExcel;
import helpers.UsuarioHelper;
import repositorios.RepositorioActividades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MedicionesController {
    private ImportarExcel importador = new ImportarExcel();
    private RepositorioActividades repositorioActividades  = new RepositorioActividades();

    public ModelAndView mostrarCargarMediciones(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        System.out.println();
        return new ModelAndView(parametros, "org/org_registrar_mediciones.hbs");
    }

    public Response cargarMediciones(Request request, Response response) {
        Organizacion organizacion = (Organizacion) UsuarioHelper.usuarioLogueado(request).getActor();

        String archivo_path = "src/test/java/resources/" + request.queryParams("archivo_mediciones"); // com odeberia funcar
        List<TipoConsumo> consumoList = EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + TipoConsumo.class.getName())
                .getResultList();

        Map<String, TipoConsumo> tipoConsumoMap = consumoList
                .stream()
                .collect(Collectors.toMap(TipoConsumo::getDescripcion, item -> item));

                //String archivo_path = "src/test/java/resources/Excel para modulo importador.xlsx";
       // "src/test/java/resources/Excel para modulo importador.xlsx"
        System.out.println( " archivo path: "+  archivo_path);

        List<Actividad> mediciones = importador.importar(archivo_path);
        System.out.println( " mediciones"+  mediciones.toString());

        //organizacion.setMediciones(mediciones);


/*        Child childEntity = new Child();
        Child savedChildEntity = childRepository.save(childEntity);
        parentEntity.addChild(savedChildEntity);
        parentEntity.getChildren();

        parentRepository.save(parentEntity);

 */
                /*

        int index = 0;
        for (index = 0; index <  mediciones.size(); index++ ){
            Actividad actividad = mediciones.get(index);


            System.out.println(  actividad.getClass().getName());
         */
        /* --------- Comparadores */
        ActividadConsumo actividadConsumoComparador = new ActividadConsumo();

        Logistica logisticaComparador = new Logistica();

        List<ActividadConsumo> actividadConsumos =  mediciones.stream().filter(actividad1 ->{
                        System.out.println(actividad1.getClass().toString());
                        System.out.println( actividadConsumoComparador.getClass().toString());
                     return actividad1.getClass().getName() == actividadConsumoComparador.getClass().getName();
            }).collect(Collectors.toList())
                    .stream()
                    .map(actividad1 -> ( ActividadConsumo) actividad1 ).collect(Collectors.toList());

            List<Logistica> logisticas =  mediciones.stream().filter(actividad1 ->
                            actividad1.getClass().getName() == logisticaComparador.getClass().getName())
                    .collect(Collectors.toList()).stream()
                    .map(actividad1 -> ( Logistica) actividad1 ).collect(Collectors.toList());

        System.out.println(actividadConsumos.toString());
        System.out.println(logisticas.toString());


        actividadConsumos.forEach(this::persitirActividadConsumo);
        logisticas.forEach(this::persistirLogistica);


/*

            Actividad actividadToSave;
            switch (actividad.getClass().getName() ){
                case ActividadConsumo.class.getName().toString():
                    actividadToSave = new ActividadConsumo();
                case "Logistica":
                    actividadToSave = new Logistica();
                case "TipoActividadConsumo":
                    actividadToSave = new ActividadConsumo();
                default:
                    System.out.println("error de tipso xd");
            }
            actividadToSave =  actividad ;


            this.repositorioActividades.guardar(actividadToSave);

        }
         */
        organizacion.getMediciones().addAll(mediciones);

        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(organizacion);
        EntityManagerHelper.getEntityManager().getTransaction().commit();


        response.redirect("/organizacion/principal");
        return response;
    }

    private void persistirLogistica(Logistica logistica) {
        EntityManagerHelper.getEntityManager().persist(logistica.getPeriodicidad());
        EntityManagerHelper.getEntityManager().persist(logistica.getMedioTransporte());
        EntityManagerHelper.getEntityManager().persist(logistica);
    }

    private void persitirActividadConsumo(ActividadConsumo actividad) {
       // EntityManagerHelper.getEntityManager().persist(actividad.getPeriodicidad());
        System.out.println(actividad.getConsumo().toString());
/*
        EntityManagerHelper.getEntityManager().merge(actividad.getConsumo().getTipoConsumo());

        EntityManagerHelper.getEntityManager().persist(actividad.getConsumo().getTipoConsumo());
        EntityManagerHelper.getEntityManager().persist(actividad.getConsumo());
  */

        repositorioActividades.guardar(actividad);
    }
}

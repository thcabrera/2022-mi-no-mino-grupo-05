package models;

import domain.db.EntityManagerHelper;
import domain.entidades.*;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAreasEnMemoria {

    private List<Area> areas;

    public RepositorioDeAreasEnMemoria(){
        areas = getListaOrgsEjemplo();
    }

    /* hardcodeo para no levantar workbench*/
    private List<Area> getListaOrgsEjemplo() {
        List<Organizacion> orgs = new ArrayList<>();
        Organizacion lennyDespe = new Organizacion("SA", new TipoOrg("empresa") , null, null);
        lennyDespe.setNombre("Despegando con Lenny SA");
        Area contable = new Area("Contable", lennyDespe);
        contable.setId(1);
        Area limpieza = new Area("Limpieza", lennyDespe);
        limpieza.setId(2);

        Organizacion aterriTito = new Organizacion("UNI", new TipoOrg("educativa"), null, null);
        aterriTito.setNombre("Aterrizando con Tito SRL");
        Area coaching = new Area("Coaching", aterriTito);
        coaching.setId(3);
        orgs.add(lennyDespe);
        orgs.add(aterriTito);

        Persona lenny = new Persona("lean", "lienard",43814111, Documentacion.DNI);
        Persona tito = new Persona("tito", "lecaldare",43812754, Documentacion.DNI);

        List<Area> areasA = new ArrayList<>(aterriTito.getAreas());
        areasA.addAll(lennyDespe.getAreas());
        return areasA;
    }

    public List<Area> buscarTodos(){
        return areas;
    }

    // el objeto si lo encuentra, null si no lo encuentra
    public Area buscar(int id) {
        for (Area a: areas){
            if (a.getId() == id){
                return a;
            }
        }
        return null;
    }

    public void guardar(Area area) {
        areas.add(area);
    }

    public void eliminar(Area area) {
        areas.remove(area);
    }



}

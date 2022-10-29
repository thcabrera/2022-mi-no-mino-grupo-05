package repositorios.enMemoria;

import domain.entidades.*;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeOrganizacionesEnMemoria {

    private List<Organizacion> organizaciones;

    public RepositorioDeOrganizacionesEnMemoria(){
        organizaciones = getListaOrgsEjemplo();
    }

    /* hardcodeo para no levantar workbench*/
    private List<Organizacion> getListaOrgsEjemplo() {
        List<Organizacion> orgs = new ArrayList<>();
        Organizacion lennyDespe = new Organizacion("SA", new TipoOrg("empresa") , null, null);
        lennyDespe.setId(2);
        lennyDespe.setNombre("Despegando con Lenny SA");
        Area contable = new Area("Contable", lennyDespe);
        contable.setId(1);
        Area limpieza = new Area("Limpieza", lennyDespe);
        limpieza.setId(2);

        Organizacion aterriTito = new Organizacion("UNI", new TipoOrg("educativa"), null, null);
        aterriTito.setId(1);
        aterriTito.setNombre("Aterrizando con Tito SRL");
        Area coaching = new Area("Coaching", aterriTito);
        coaching.setId(3);
        orgs.add(lennyDespe);
        orgs.add(aterriTito);

//        Persona lenny = new Persona("lean", "lienard",43814111, Documentacion.DNI);
//        Persona tito = new Persona("tito", "lecaldare",43812754, Documentacion.DNI);

        return orgs;
    }

    public List<Organizacion> buscarTodos(){
        return organizaciones;
    }

    // el objeto si lo encuentra, null si no lo encuentra
    public Organizacion buscar(int id) {
        for (Organizacion o: organizaciones){
            if (o.getId() == id){
                return o;
            }
        }
        return null;
    }

    public void guardar(Organizacion organizacion) {
        organizaciones.add(organizacion);
    }

    public void eliminar(Organizacion organizacion) {
        organizaciones.remove(organizacion);
    }



}

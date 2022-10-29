package models.enMemoria;

import domain.entidades.Organizacion;
import domain.viaje.Trayecto;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeTrayectosEnMemoria {

    private List<Trayecto> trayectos = new ArrayList<>();

    public RepositorioDeTrayectosEnMemoria(){
        Organizacion org1 = new Organizacion(null, null, null, null);
        org1.setId(1);
        org1.setNombre("DespeLenny");
        Trayecto t1 = new Trayecto(new ArrayList<>());
        t1.setOrganizacion(org1);
        Trayecto t2 = new Trayecto(new ArrayList<>());
        t2.setOrganizacion(org1);
        Organizacion org2 = new Organizacion(null, null, null, null);
        org2.setNombre("Aterrizando con Tito");
        Trayecto t3 = new Trayecto(new ArrayList<>());
        t3.setOrganizacion(org2);
        this.guardar(t1);
        this.guardar(t2);
        this.guardar(t3);
    }

    public List<Trayecto> buscarTodos(){
        return this.trayectos;
    }

    public void guardar(Trayecto trayecto){
        if (trayecto.getId() == null)
            trayecto.setId(this.trayectos.size() + 1);
        this.trayectos.add(trayecto);
    }

    public Trayecto buscar(int id){
        for(Trayecto trayecto: this.trayectos){
            if (trayecto.getId() == id){
                return trayecto;
            }
        }
        return null;
    }

    public void eliminar(Trayecto trayecto){
        this.trayectos.remove(trayecto);
    }

}

package domain.entidades;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private Organizacion organizacion;
    private List<Persona> miembros;
    private String nombre;



    //  ----------  GETTERS & SETTERS  ----------

    public Area(String nombre, Organizacion organizacion) {
        this.organizacion = organizacion;
        this.nombre = nombre;
        this.miembros = new ArrayList<Persona>();
        organizacion.agregarArea(this);
    }
    public List<Persona> getMiembros(){
        return miembros;
    }
    //  ----------  AGREGAR MIEMBROS  ----------
    public void agregarMiembro(Persona persona){
        miembros.add(persona);
    }
}

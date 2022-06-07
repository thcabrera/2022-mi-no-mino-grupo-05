package domain.entidades;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private Organizacion organizacion;
    private List<Persona> miembros;


    //  ----------  GETTERS & SETTERS  ----------

    public Area(Organizacion organizacion) { //TO: consultar
        this.organizacion = organizacion;
        this.miembros = new ArrayList<Persona>();
        organizacion.generarArea(this);
    }
    public List<Persona> getMiembros(){
        return miembros;
    }
    //  ----------  AGREGAR MIEMBROS  ----------
    public void agregarMiembro(Persona persona){
        miembros.add(persona);
    }
}

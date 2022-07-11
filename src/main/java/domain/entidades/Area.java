package domain.entidades;

import java.util.ArrayList;
import java.util.List;

public class sArea {
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

    //  ----------  CALCULO HC  ----------
    public Double calculoHC(){
        return this.miembros.stream().mapToDouble(p -> p.calcularHC(this.organizacion)).sum();
    }

    public Double indicadorHCporMiembro(){
        return this.calculoHC() / this.getMiembros().size();
    }
}
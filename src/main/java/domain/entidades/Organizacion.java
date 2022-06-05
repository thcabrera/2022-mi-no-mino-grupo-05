package domain.entidades;

import domain.Direccion;
import domain.mediciones.consumos.Actividad;

import java.util.List;

public class Organizacion {
    private String razonSocial;
    private TipoOrg tipo;
    private Direccion ubicacion;
    private List<Area> areas;
    private Clasificacion clasificacion;
    private List<Actividad> mediciones;

    public void aceptarEmpleado(Persona persona, Area area){
        //TODO
    }

    public List<Persona> miembros(Area area){
        //TODO
        return null;
    }
}

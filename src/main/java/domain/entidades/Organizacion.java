package domain.entidades;

import domain.Direccion;

import java.util.List;

public class Organizacion {
    private String razonSocial;
    private TipoOrg tipo;
    private Direccion ubicacion;
    private List<Area> areas;
    private Clasificacion clasificacion;

    public void aceptarEmpleado(Persona persona, Area area){
        //TODO
    }

    public List<Persona> miembros(Area area){
        //TODO
        return null;
    }
}

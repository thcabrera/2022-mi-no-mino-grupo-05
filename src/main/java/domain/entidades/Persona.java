package domain.entidades;

import domain.viaje.Trayecto;

import java.util.List;

public class Persona {
    private String nombre;
    private String apellido;
    private Integer nroDocumento;
    private Documentacion tipoDoc;
    private List<Area> listaAreas;
    private List<Trayecto> trayectos;

    public void solicitarAlta(Organizacion org, Area area) {
        //TODO
    }
}

package domain.entidades;

import domain.Direccion;
import domain.viaje.Trameable;
import domain.viaje.Trayecto;
import domain.viaje.particular.Combustible;
import domain.viaje.particular.TParticular;
import domain.viaje.particular.TipoParticular;
import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TPublico;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Persona {
    private String nombre;
    private String apellido;
    private Integer nroDocumento;
    private Documentacion tipoDoc;
    private List<Area> listaAreas;
    private List<Trayecto> trayectos;

    //  ----------  GETTERS & SETTERS  ----------
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getNroDocumento() {
        return nroDocumento;
    }
    public List<Area> getListaAreas() {
        return this.listaAreas;
    }

    public Persona(String nombre, String apellido, Integer nroDocumento, Documentacion tipoDoc) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroDocumento = nroDocumento;
        this.tipoDoc = tipoDoc;
        this.listaAreas = new ArrayList<Area>();
        this.trayectos = new ArrayList<Trayecto>();
    }

    //  ----------  SOLICITUDES  ----------
    public void solicitarAlta(Organizacion org, Area area) {
        Solicitud solicitud = new Solicitud(this, area);
        org.recibirSolicitud(solicitud);
    }

    public void altaAceptada(Area area){
        this.listaAreas.add(area);
    }


    //  ----------  TRAYECTOS  ----------

    public void darDeAltaTrayecto(Trameable ... tramos){
        Trayecto trayecto = crearTrayectoVacio();
        trayecto.agregarTramos(tramos);
        trayectos.add(trayecto);
    }

    public Trayecto crearTrayectoVacio(){
        List<Trameable> tramos = new ArrayList<Trameable>();
        return new Trayecto(tramos);
    }

    //  ----------  TRAMOS COMPARTIDOS  ----------

    public List<Trameable> verTramosCompartidos(List<Trameable> tramos){
        return (List<Trameable>) tramos.stream().filter(t -> t.getEsCompartido());
    }
}

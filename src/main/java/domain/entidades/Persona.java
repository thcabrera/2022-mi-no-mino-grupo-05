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

    public void solicitarUnirse(Trameable tramo){

    }

    //  ----------  TRAYECTOS  ----------

    public void agregarTrayecto(Trameable ... tramos){
        Trayecto trayecto = crearTrayectoVacio();
        trayecto.agregarTramos(tramos);
        trayectos.add(trayecto);
    }

    public Trayecto crearTrayectoVacio(){
        List<Trameable> tramos = new ArrayList<Trameable>();
        return new Trayecto(tramos);
    }

    //  ----------  TRAMOS COMPARTIDOS  ----------

    // Ya existen los tramos compartidos, y ya elegio a CUAL unirse (invocando sumarseATramoCompartido())
    /*
    tramo1 = esCompartido, lucas, cama
    tramo2 = noEs, lean, gol
    tramo3 = esCompartido, tito, bici
     */

    public void sumarseATramoCompartido(Trameable tramo){
        //this.
    }


}

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
import java.util.stream.Collectors;

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

    public List<Trameable> getTramos(){
        List<Trameable> tramos = new ArrayList<Trameable>();
        this.trayectos.forEach(trayecto -> tramos.addAll(trayecto.getTramos()));

        return tramos;
    }

    public List<Trameable> getTramosCompartidosPropios(){
        List<Trameable> tramos = new ArrayList<Trameable>();
        this.trayectos.forEach(trayecto -> tramos.addAll(trayecto.getTramos()));

        return tramos.stream()
                .filter(t->t.getEsCompartido() )
                .filter(t->t.getPropietario().equals(this) )
                .collect(Collectors.toList());

    }

    //  ----------  TRAMOS COMPARTIDOS  ----------
    // Flujo de posibilidades
    //  -Lenny quiere crearlo:
    //      esCompartido = true;
    //      tramo1 = new TParticular(,,,,esCompartido);
    //      tramo1.setPropietario(this);
    //      lennyTrayecto.add(tramo1);


    //  -Marmo quiere sumarse:
    //      1- preguntar que tramos compartidos hay en la organizacion: getTramosCompartidos
    //      2- tramoSeleccionado = seleccionarTramo(tramo);
    //      3- marmoTrayecto.add(tramoSeleccionado);

    public List<Trameable> getTramosCompartidos(Organizacion organizacion){
        List<Trameable> tramosCompartidos = organizacion.getTramosCompartidos();
        return tramosCompartidos;
    }
}

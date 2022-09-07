package domain.entidades;

import domain.viaje.Trameable;
import domain.viaje.Trayecto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class Persona {
    private String nombre;
    private String apellido;
    private Integer nroDocumento;
    private Documentacion tipoDoc;
    private List<Area> listaAreas;
    private List<Trayecto> trayectos;

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

    //  ----------  CALCULO HC  ----------

    public Double calcularHC(Organizacion organizacion){ // Cálculo de HC de un miembro de una organización
        return this.trayectos
                .stream().filter(t -> t.getOrganizacion().equals(organizacion))
                .mapToDouble(t -> t.calculoHC(this))
                .sum();
    }

    public Double impactoEnOrganizacion(Organizacion organizacion){
        return 0.0; //100 * this.calcularHC(organizacion) / organizacion.calculoHC(); // Porcentaje de impacto en organizacion
    }

}

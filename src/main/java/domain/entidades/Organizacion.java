package domain.entidades;

import domain.Direccion;
import domain.entidades.contacto.Contacto;
import domain.entidades.contacto.Mensaje;
import domain.mediciones.consumos.Periodicidad;
import domain.mediciones.consumos.actividades.Actividad;
import domain.viaje.Trameable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter

@Entity
@Table(name="organizacion")
public class Organizacion {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="razon_social")
    private String razonSocial;

    @ManyToOne
    @JoinColumn(name="tipo_org_id", referencedColumnName = "id")
    private TipoOrg tipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private Direccion ubicacion;

    @Transient
    private List<Area> areas;

    @ManyToOne
    @JoinColumn(name="clasificacion_id", referencedColumnName = "id")
    private Clasificacion clasificacion;

    @Transient
    private List<Actividad> mediciones;

    @Transient
    private List<Solicitud> solicitudes;


    private List<Contacto> contactos;

    //  ----------  GETTERS & SETTERS  ----------

    public Organizacion(String razonSocial, TipoOrg tipo, Direccion ubicacion, Clasificacion clasificacion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.areas  = new ArrayList<>();
        this.clasificacion = clasificacion;
        this.mediciones  = new ArrayList<>();
        this.solicitudes  = new ArrayList<>();
        this.contactos = new ArrayList<>();
    }

    // ----------- ACTIVIDADES ---------

    public void agregarActividad(Actividad actividad){
        mediciones.add(actividad);
    }

    //  ----------  SOLICITUD  ----------

    public void aceptarEmpleado(Persona persona, Area area) {
        Solicitud solicitud = getSolicitudDe(persona, area); // NO Esta funcionando
        if (solicitud != null) {
            this.solicitudes.remove(solicitud);
            System.out.printf("%s %s aceptado/a y removido/a de solicitantes correctamente", solicitud.getSolicitante().getNombre(), persona.getApellido());
            area.agregarMiembro(persona);
            persona.altaAceptada(area);
        }
    }

    public void rechazarSolicitud(Persona persona, Area area){ // habria q notificar el rechazo de la solicitud a la Persona?
        Solicitud solicitud = getSolicitudDe(persona, area);
        if (solicitud != null) {
            this.solicitudes.remove(solicitud);
            System.out.printf("%s %s rechazado/a y removido/a de solicitantes correctamente", persona.getNombre(), persona.getApellido());
        }
    }
    public void recibirSolicitud(Solicitud solicitud){
        solicitudes.add(solicitud);
    }

    public Solicitud getSolicitudDe(Persona persona, Area area){

        return this.solicitudes.stream()
                .filter(s -> s.getSolicitante() == persona)
                .findAny()
                .orElse(null);
    }
    //  ----------  DAR DE ALTA AREAS  ----------
    public void agregarArea(Area nuevaArea){
        this.areas.add(nuevaArea);
    }

    public List<Persona> miembrosEn(Area area){
        return area.getMiembros();
    }

    public List<Persona> getMiembros(){ // TODO: hacer q no tenga contenga repetidos, o q los elimine al final
        List<Persona> miembrosTotales = new ArrayList<Persona>();
        areas.forEach(area-> miembrosTotales.addAll(area.getMiembros()));

        return miembrosTotales
                .stream().distinct()
                .collect(Collectors.toList()); //distinct elimina repetidos
    }

    //  ----------  TRAMOS COMPARTIDOS  ----------
    // No me parece que sirva de nada esta funcion
    public List<Trameable> getTramosCompartidos() {
        List<Trameable> tramosCompartidos = new ArrayList<Trameable>();
        this.getMiembros().forEach(miembro -> tramosCompartidos.addAll(miembro.getTramos()));

        return tramosCompartidos
                .stream()
                .filter(t -> t.getEsCompartido())
                .collect(Collectors.toList());

        //List<Trameable> tramosCompartidos = organizacion.getTramosCompartidos();
        //return (List<Trameable>) tramos.stream().filter(t -> t.getEsCompartido());
    }

    //  ----------  CALCULO HC  ----------

    public Double calculoHC(Periodicidad periodo){
        return this.calculoHCActividades(periodo) + this.calculoHCTrayectos();
    }

    // no le pedimos el calculo directamente a las areas porque puede ser que una persona
    // este en multiples áreas
    private Double calculoHCTrayectos() {
        return this.getMiembros().stream()
                    .mapToDouble(miembro -> miembro.calcularHC(this))
                    .sum();
    }

    private Double calculoHCActividades(Periodicidad periodo) {
        return this.mediciones.stream()
                .mapToDouble(a -> a.calculoHC(periodo))
                .sum();
    }

    //----------- Notificaciones --------------
    public List<Contacto> getContacto() {
        return contactos;
    }
    public void agregarContactos(Contacto ... contactos){
        Collections.addAll(this.contactos, contactos);
    }
    public void notificar(Mensaje mensaje){
        this.contactos.forEach(c -> c.notificar(mensaje));
    }
}

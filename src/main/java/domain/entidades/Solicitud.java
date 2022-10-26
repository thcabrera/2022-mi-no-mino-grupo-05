package domain.entidades;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Table(name="solicitud")
public class Solicitud {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="persona_id", referencedColumnName = "id")
    private Persona solicitante;
    @ManyToOne
    @JoinColumn(name="area_id", referencedColumnName = "id")
    private Area areaSolicitada;

    @ManyToOne
    @JoinColumn(name="org_id", referencedColumnName = "id")
    private Organizacion organizacion;

    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private EstadoSolicitud estado;

    public Solicitud(){

    }

    public Solicitud(Persona solicitante, Area areaSolicitada) {
        this.solicitante = solicitante;
        this.areaSolicitada = areaSolicitada;
    }

    public Persona getSolicitante() {
        return solicitante;
    }

    public Area getAreaSolicitada() {
        return areaSolicitada;
    }
}

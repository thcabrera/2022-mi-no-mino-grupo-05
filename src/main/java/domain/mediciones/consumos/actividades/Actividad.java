package domain.mediciones.consumos.actividades;

import domain.entidades.Organizacion;
import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "actividad")
@DiscriminatorColumn(name = "discriminador", length = 18)
public abstract class Actividad {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "periodicidad_id", referencedColumnName = "id")
    private Periodicidad periodicidad;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organizacion organizacion;

    public Actividad(Periodicidad periodicidad){
        this.periodicidad = periodicidad;
    }

    public abstract Double calculoHC(Periodicidad periodicidad);

}
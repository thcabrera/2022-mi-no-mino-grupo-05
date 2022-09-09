package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "actividad")
@DiscriminatorValue("tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Actividad {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "periodicidad_id", referencedColumnName = "id")
    private Periodicidad periodicidad;

    public Actividad(Periodicidad periodicidad){
        this.periodicidad = periodicidad;
    }

    public abstract Double calculoHC(Periodicidad periodicidad);

}

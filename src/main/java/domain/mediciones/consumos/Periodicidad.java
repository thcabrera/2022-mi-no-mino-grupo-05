package domain.mediciones.consumos;

import javax.persistence.*;

@Entity
@Table(name = "periodicidad")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminador")
public abstract class Periodicidad {

    @Id
    @GeneratedValue
    private Integer id;

    public abstract boolean coincide(Periodicidad periodicidad);

    public abstract Integer getAnio();

    public abstract Double obtenerPorcentaje(Periodicidad periodicidad);

}
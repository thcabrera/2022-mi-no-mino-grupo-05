package domain.entidades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;

import javax.persistence.*;

// de un sector solo nos interesa calcular su huella de carbono, absolutamente nada mas
@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 10)
@Table(name = "sector")
public abstract class Sector {

    @Id
    @GeneratedValue
    protected Integer id;

    @Column(name = "descripcion")
    protected String descripcion;

    public abstract Double calculoHC(Integer anio, Integer mes);

}

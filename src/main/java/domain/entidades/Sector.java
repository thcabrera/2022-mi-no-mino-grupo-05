package domain.entidades;

import domain.mediciones.consumos.Periodicidad;

import javax.persistence.*;

// de un sector solo nos interesa calcular su huella de carbono, absolutamente nada mas
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "sector")
public abstract class Sector {

    @Id
    @GeneratedValue
    protected Integer id;

    public abstract Double calculoHC(Periodicidad periodo);

}

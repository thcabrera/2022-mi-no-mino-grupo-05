package domain.viaje;

import domain.entidades.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="tramo")
public abstract class Trameable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column (name = "tipo_tramo")
    private String tipoTramo;

    // Returns genericos para poder hacer el override (Previamente era una interfaz).

    public Double consumoPorKM(){
        return 0.0;
    }

    public Double calcularDistanciaTramo(){
        return 0.0;
    }

    public boolean getEsCompartido(){
        return false;
    }

    public Persona getPropietario(){
        return null;
    }

    public Double calculoHC(Persona persona){
        return 0.0;
    }
}

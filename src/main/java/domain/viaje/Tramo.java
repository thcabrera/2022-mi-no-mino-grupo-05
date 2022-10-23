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
public abstract class Tramo {
    @Id
    @GeneratedValue
    private Integer id;

    @Column (name = "tipo_tramo")
    private String tipoTramo;

    // Returns genericos para poder hacer el override (Previamente era una interfaz).

    public abstract Double consumoPorKM();

    public abstract Double calcularDistanciaTramo();

    public abstract boolean getEsCompartido();

    public abstract Persona getPropietario();

    public abstract Double calculoHC(Persona persona);

    public abstract String obtenerInicio();

    public abstract String obtenerFin();

    public abstract String obtenerTipo();

    public TramoDTO convertirADTO(){
        return new TramoDTO(this);
    }

    public class TramoDTO{

        public String puntoInicio;
        public String puntoFin;
        public String tipo;

        public TramoDTO(Tramo tramo){
            this.puntoFin = tramo.obtenerFin();
            this.puntoInicio = tramo.obtenerInicio();
            this.tipo = tramo.obtenerTipo();
        }

    }

}

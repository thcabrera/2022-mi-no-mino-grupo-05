package domain.viaje.privado.limpio;

import domain.Direccion;
import domain.entidades.Persona;
import domain.mediciones.services.calculoDistancia.ServicioDistancia;
import domain.mediciones.services.calculoDistancia.entities.Distancia;
import domain.viaje.Tramo;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.persistence.*;

@Getter
@Entity
@Table(name="tramo_limpio")
public class TramoLimpio extends Tramo {

    @Column(name="tipo")
    public String tipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_inicio_id", referencedColumnName = "id")
    private Direccion direccionInicio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_fin_id", referencedColumnName = "id")
    private Direccion direccionFin;

    //  ----------  GETTERS & SETTERS  ----------

    public TramoLimpio(String tipo, Direccion direccionInicio, Direccion direccionFin) {
        this.tipo = tipo;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
    }

    public boolean getEsCompartido(){
        return false;
    }
    @Override
    public Persona getPropietario() {
        return null;
    }

    //  ----------  CONSUMO  ----------
    @Override
    public Double calculoHC(Persona persona){
        return 0.0;
    }

    @Override
    public String obtenerInicio() {
        return this.direccionInicio.toString();
    }

    @Override
    public String obtenerFin() {
        return this.direccionFin.toString();
    }

    @Override
    public String obtenerTipo() {
        return this.tipo;
    }

    @Override
    public Double consumoPorKM(){
        return 0.0;
    }

    @Override
    public Double calcularDistanciaTramo() {
        return this.distanciaTramo(new ServicioDistancia()).valor;
    }

    @SneakyThrows
    public Distancia distanciaTramo(ServicioDistancia servicio){
        return servicio.calcularDistanciaTramo(this.direccionInicio, this.direccionFin);
    }

}

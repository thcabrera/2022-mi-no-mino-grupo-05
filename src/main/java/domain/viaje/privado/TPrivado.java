package domain.viaje.privado;

import domain.Direccion;
import domain.entidades.Persona;
import domain.services.calculoDistancia.ServicioDistancia;
import domain.services.calculoDistancia.entities.Distancia;
import domain.viaje.Trameable;
import lombok.SneakyThrows;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TPrivado extends Trameable {

   // @ManyToOne
   // @JoinColumn(name = "direccion_inicio_id", referencedColumnName = "id")
    @Transient
    protected Direccion direccionInicio;

   // @ManyToOne
   // @JoinColumn(name = "direccion_fin_id", referencedColumnName = "id")
    @Transient
    protected Direccion direccionFin;

    @Override
    public Double calcularDistanciaTramo() {
        return this.distanciaTramo(new ServicioDistancia()).valor;
    }

    @SneakyThrows //????????????????
    public Distancia distanciaTramo(ServicioDistancia servicio){
        return servicio.calcularDistanciaTramo(this.direccionInicio, this.direccionFin);
    }

    @Override
    public Double calculoHC(Persona persona){
        return 0.0;
    };

}

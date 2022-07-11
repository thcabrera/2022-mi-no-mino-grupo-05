package domain.viaje.privado;

import domain.Direccion;
import domain.services.calculoDistancia.ServicioDistancia;
import domain.services.calculoDistancia.entities.Distancia;
import domain.viaje.Trameable;
import lombok.SneakyThrows;

public abstract class TPrivado implements Trameable {
    protected Direccion direccionInicio;
    protected Direccion direccionFin;

    @Override
    public Double calcularDistanciaTramo() {
        return this.distanciaTramo(new ServicioDistancia()).valor;
    }

    @SneakyThrows //????????????????
    public Distancia distanciaTramo(ServicioDistancia servicio){
        return servicio.calcularDistanciaTramo(this.direccionInicio, this.direccionFin);
    }

}

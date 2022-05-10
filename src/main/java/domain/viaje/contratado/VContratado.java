package domain.viaje.contratado;

import domain.Direccion;
import domain.viaje.Trameable;

public class VContratado implements Trameable {
    private Servicio tipoTransporte;
    private Direccion direccionInicio;
    private Direccion direccionFin;

    public Integer consumo(){
        //TODO
        return 0;
    }

    @Override
    public Integer calcularDistanciaTramo() {
        //TODO
        return null;
    }
}

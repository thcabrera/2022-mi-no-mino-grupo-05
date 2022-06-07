package domain.viaje.contratado;

import domain.Direccion;
import domain.entidades.Persona;
import domain.viaje.Trameable;

public class TContratado implements Trameable {
    private Servicio tipoTransporte;
    private Direccion direccionInicio;
    private Direccion direccionFin;

    private Persona propietario;

    //  ----------  GETTERS & SETTERS  ----------
    public TContratado(Servicio tipoTransporte, Direccion direccionInicio, Direccion direccionFin) {
        this.tipoTransporte = tipoTransporte;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
    }

    public Persona getPropietario() {
        return propietario;
    }

    //  ----------  CONSUMO  ----------
    public Integer consumo(){
        //TODO
        return 0;
    }

    //  ----------  CALCULO DE DISTANCIA  ----------
    @Override
    public Integer calcularDistanciaTramo() {
        //TODO
        return null;
    }
}

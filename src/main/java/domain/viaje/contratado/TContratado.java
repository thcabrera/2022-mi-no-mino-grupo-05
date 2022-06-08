package domain.viaje.contratado;

import domain.Direccion;
import domain.entidades.Persona;
import domain.viaje.Trameable;

public class TContratado implements Trameable {
    private Servicio tipoTransporte;
    private Direccion direccionInicio;
    private Direccion direccionFin;

    private Persona propietario;
    private boolean esCompartido;

    //  ----------  GETTERS & SETTERS  ----------
    public TContratado(Servicio tipoTransporte, Direccion direccionInicio, Direccion direccionFin, boolean esCompartido) {
        this.tipoTransporte = tipoTransporte;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
        this.esCompartido = esCompartido;
        this.propietario = null;
    }
    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    @Override
    public Persona getPropietario() {
        return propietario;
    }

    public boolean getEsCompartido(){
        return esCompartido;
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

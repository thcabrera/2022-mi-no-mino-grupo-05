package domain.viaje.privado.contratado;

import domain.Direccion;
import domain.entidades.Persona;
import domain.viaje.privado.TPrivado;

public class TContratado extends TPrivado {
    private Servicio tipoTransporte;
    private Direccion direccionInicio;
    private Direccion direccionFin;

    private Persona propietario;
    private boolean esCompartido;

    private  Double consumoPorKM;

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
    public Double calculoHC(Persona persona){
        if (this.esElPropietario(persona)){
            return this.consumoPorKM() * this.calcularDistanciaTramo();
        }
        else
            return 0.0;
    }

    public Boolean esElPropietario(Persona persona){
        return this.propietario == persona;
    }

    @Override
    public Persona getPropietario() {
        return propietario;
    }

    @Override
    public Double consumoPorKM() {
        return this.consumoPorKM;
    }

    public boolean getEsCompartido(){
        return esCompartido;
    }
}

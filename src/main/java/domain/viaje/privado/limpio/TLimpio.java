package domain.viaje.privado.limpio;

import domain.Direccion;
import domain.entidades.Persona;
import domain.viaje.privado.TPrivado;

public class TLimpio extends TPrivado {
    public String tipo;
    private Direccion direccionInicio;
    private Direccion direccionFin;



    //  ----------  GETTERS & SETTERS  ----------

    public TLimpio(String tipo, Direccion direccionInicio, Direccion direccionFin) {
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
    public Integer consumo(){
        return 0;
    }

    //  ----------  CALCULO DE DISTANCIA  ----------
    @Override
    public Integer calcularDistanciaTramo() {
        return null;
    }


}

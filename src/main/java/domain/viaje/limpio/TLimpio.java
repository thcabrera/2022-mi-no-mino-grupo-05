package domain.viaje.limpio;

import domain.Direccion;
import domain.viaje.Trameable;

public class TLimpio implements Trameable {
    public String tipo;
    private Direccion direccionInicio;
    private Direccion direccionFin;

    //  ----------  GETTERS & SETTERS  ----------

    public TLimpio(String tipo, Direccion direccionInicio, Direccion direccionFin) {
        this.tipo = tipo;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
    }

    //  ----------  CONSUMO  ----------

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

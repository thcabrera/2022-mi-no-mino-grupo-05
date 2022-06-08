package domain.viaje.particular;

import domain.Direccion;
import domain.entidades.Persona;
import domain.viaje.Trameable;

public class TParticular implements Trameable {
    private Combustible tipoCombustible;
    private TipoParticular tipoParticular;
    private Direccion direccionInicio;
    private Direccion direccionFin;
    private Boolean esCompartido;
    private Persona propietario;

    //  ----------  GETTERS & SETTERS  ----------

    public TParticular(Combustible tipoCombustible, TipoParticular tipoParticular, Direccion direccionInicio, Direccion direccionFin, Boolean esCompartido) {
        this.tipoCombustible = tipoCombustible;
        this.tipoParticular = tipoParticular;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
        this.esCompartido = esCompartido;
    }

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
        return null;
    }
}

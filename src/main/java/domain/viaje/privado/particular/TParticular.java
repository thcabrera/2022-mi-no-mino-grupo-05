package domain.viaje.privado.particular;

import domain.Direccion;
import domain.entidades.Persona;
import domain.viaje.privado.TPrivado;

public class TParticular extends TPrivado {
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
        this.propietario = null;
    }

    @Override
    public Double calculoHC(){
        return 0.0;
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
        return null;
    }
}

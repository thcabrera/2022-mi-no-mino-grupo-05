package domain.viaje.particular;

import domain.Direccion;
import domain.viaje.Trameable;

public class VParticular implements Trameable {
    private Combustible tipoCombustible;
    private TipoParticular tipoParticular;
    private Direccion direccionInicio;
    private Direccion direccionFin;
    private Integer idDeViaje;
    private Boolean esCompartido;

    public Integer consumo(){
        //TODO
        return 0;
    }

    @Override
    public Integer calcularDistanciaTramo() {
        return null;
    }
}

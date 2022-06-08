package domain.viaje;

import domain.Direccion;

public interface Trameable {
    public Integer consumo();
    public Integer calcularDistanciaTramo();

    public boolean getEsCompartido();
}

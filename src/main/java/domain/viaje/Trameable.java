package domain.viaje;

import domain.Direccion;
import domain.entidades.Persona;

public interface Trameable {
    public Integer consumo();
    public Integer calcularDistanciaTramo();

    public boolean getEsCompartido();

    public Persona getPropietario();
}

package domain.viaje;

import domain.Direccion;
import domain.entidades.Persona;

public interface Trameable {
    public Double consumoPorKM();
    public Double calcularDistanciaTramo();

    public boolean getEsCompartido();

    public Persona getPropietario();

    public Double calculoHC(Persona persona);
}

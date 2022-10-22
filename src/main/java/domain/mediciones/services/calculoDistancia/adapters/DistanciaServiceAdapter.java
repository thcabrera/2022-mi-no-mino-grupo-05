package domain.mediciones.services.calculoDistancia.adapters;

import domain.Direccion;
import domain.mediciones.services.calculoDistancia.entities.Distancia;

import java.io.IOException;

public interface DistanciaServiceAdapter {

    Distancia calculoDistancia(Direccion direccionInicio, Direccion direccionFin) throws IOException;
}

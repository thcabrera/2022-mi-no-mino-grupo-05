package domain.viaje.publico.sentido;

import domain.viaje.publico.Parada;

public interface SentidoRecorrido {
    Double getDistanciaProxParada(Parada parada);

    boolean isBetween(int indInicial, int indFinal, int indexOf);
}

package domain.viaje.publico.sentido;

import domain.viaje.publico.Parada;

public class Vuelta implements SentidoRecorrido{
    public Integer getDistanciaProxParada(Parada parada){
        return parada.getDistanciaAntParada();
    }

    public boolean isBetween(int inicio, int fin, int valor){
        return (fin < valor) && (valor <= inicio); // NO QUIERO SABER LA DE FIN
    }
}

package domain.viaje.publico.sentido;

import domain.viaje.publico.Parada;

public class Ida implements SentidoRecorrido{
    public Integer getDistanciaProxParada(Parada parada){ return parada.getDistanciaSigParada(); }

    public boolean isBetween(int inicio, int fin, int valor){
        return (inicio <= valor) && (valor < fin); //NO QUIERO SABER LA DE FIN
    }
}

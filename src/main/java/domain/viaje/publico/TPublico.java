package domain.viaje.publico;

import domain.viaje.Trameable;
import domain.viaje.publico.sentido.SentidoRecorrido;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TPublico implements Trameable {
    private Parada paradaInicio;
    private Parada paradaFin;
    private Linea linea;
    private SentidoRecorrido sentido;

    //  ----------  GETTERS & SETTERS  ----------
    public TPublico(Parada paradaInicio, Parada paradaFin, Linea linea) {
        this.paradaInicio = paradaInicio;
        this.paradaFin = paradaFin;
        this.linea = linea;
    }

    //  ----------  CONSUMO  ----------
    public Integer consumo(){
        //TODO
        return 0;
    }

    //  ----------  CALCULO DISTANCIA  ----------

    public Integer calcularDistanciaTramo(){
        List<Parada> paradasIntermedias;
        paradasIntermedias = this.solicitarParadasIntermedias();
        return calcularDistancia(paradasIntermedias);
    }

    public List<Parada> solicitarParadasIntermedias(){
        sentido = this.obtenerSentido();
        return linea.getParadasIntermedias(paradaInicio, paradaFin, sentido);
    }

    private Integer calcularDistancia(List<Parada> paradasIntermedias) {
        return paradasIntermedias.stream()
                .mapToInt(parada -> sentido.getDistanciaProxParada(parada))
                .sum();
    }

    private SentidoRecorrido obtenerSentido(){
        return linea.getSentidoRecorrido(paradaInicio,paradaFin);
    }
}




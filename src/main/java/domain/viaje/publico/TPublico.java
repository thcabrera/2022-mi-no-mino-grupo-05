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

    public TPublico(Parada paradaInicio, Parada paradaFin, Linea linea) {
        this.paradaInicio = paradaInicio;
        this.paradaFin = paradaFin;
        this.linea = linea;
    }

    public Integer consumo(){
        //TODO
        return 0;
    }

    public Integer calcularDistanciaTramo(){
        List<Parada> paradasIntermedias;
        paradasIntermedias = this.solicitarParadasIntermedias();
        return calcularDistancia(paradasIntermedias);
    }

    public List<Parada> solicitarParadasIntermedias(){
        return linea.getParadasIntermedias(paradaInicio, paradaFin);
    }


    private Integer calcularDistancia(List<Parada> paradasIntermedias) {
        return paradasIntermedias.stream()
                .mapToInt(parada -> parada.getDistanciaSigParada()) // (Parada :: getDistanciaSigParada :: ) equivalente a (p -> p.getDistanciaSigParada())
                .sum();
    }
}

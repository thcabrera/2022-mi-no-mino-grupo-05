package domain.viaje.publico;

import domain.viaje.Trameable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TPublico implements Trameable {
    private Parada paradaInicio;
    private Parada paradaFin;
    private Linea vehiculo;

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
        return vehiculo.getParadasIntermedias(paradaInicio, paradaFin);
    }


    private Integer calcularDistancia(List<Parada> paradasIntermedias) {
        return paradasIntermedias.stream()
                .mapToInt(parada -> parada.getDistanciaSigParada())
                .sum();
    }
}

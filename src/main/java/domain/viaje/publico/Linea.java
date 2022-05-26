package domain.viaje.publico;

import domain.viaje.publico.sentido.SentidoRecorrido;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Linea {
    private List<Parada> paradas;
    private String nombreLinea;

    public String getNombreLinea() {
        return nombreLinea;
    }

    public List<Parada> getParadasIntermedias(domain.viaje.publico.Parada inicio, domain.viaje.publico.Parada fin) {
        int ind_inicial = this.paradas.indexOf(inicio);
        int ind_final = this.paradas.indexOf(fin);

        return this.paradas
                .stream()
                .filter(parada -> this.isBetween(ind_inicial, ind_final, paradas.indexOf(parada)))
                .collect(Collectors.toList());
    }
    public boolean esRecorridoIda(Parada inicio, Parada fin){
        int indInicial = this.paradas.indexOf(inicio);
        int indFinal = this.paradas.indexOf(fin);
        return  indInicial < indFinal;
    }

    public void agregarParadas(Parada ... newParadas) {
        Collections.addAll(this.paradas, newParadas);
    }

    public List<Parada> getParadas(){
        return paradas;
    }

    public boolean isBetween(int inicio, int fin, int valor){
        return (inicio <= valor) && (valor < fin);
    }


}

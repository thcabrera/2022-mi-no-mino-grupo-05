package domain.viaje.publico;

import domain.viaje.publico.sentido.Ida;
import domain.viaje.publico.sentido.SentidoRecorrido;
import domain.viaje.publico.sentido.Vuelta;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public abstract class Linea {
    protected List<Parada> paradas;
    protected String nombreLinea;

    protected Double consumo;

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double nuevoConsumo) {
        this.consumo = nuevoConsumo;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public List<Parada> getParadasIntermedias(Parada inicio, Parada fin, SentidoRecorrido sentido) {
        int indInicial = this.paradas.indexOf(inicio);
        int indFinal = this.paradas.indexOf(fin);
        return this.paradas.
                    stream().
                    filter(parada -> sentido.isBetween(indInicial, indFinal, paradas.indexOf(parada))).
                    collect(Collectors.toList());
    }

    public SentidoRecorrido getSentidoRecorrido(Parada inicio, Parada fin){
        int indInicial = this.paradas.indexOf(inicio);
        int indFinal = this.paradas.indexOf(fin);
        if (indInicial < indFinal) {
            return new Ida();
        } else {
            return new Vuelta();
        }
    }

    public void agregarParadas(Parada ... newParadas) {
        Collections.addAll(this.paradas, newParadas);
    }

    public List<Parada> getParadas(){
        return paradas;
    }

}

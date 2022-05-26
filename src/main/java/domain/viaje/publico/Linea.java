package domain.viaje.publico;

import domain.viaje.publico.sentido.SentidoRecorrido;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Linea {
    protected List<Parada> paradas;
    protected String nombreLinea;

    public String getNombreLinea() {
        return nombreLinea;
    }

    public List<Parada> getParadasIntermedias(Parada inicio, Parada fin) {
        int indInicial = this.paradas.indexOf(inicio);
        int indFinal = this.paradas.indexOf(fin);
        //if( indInicial < indFinal ){ // IDA
            return this.paradas.
                        stream().
                        filter(parada -> this.isBetween(indInicial, indFinal, paradas.indexOf(parada))).
                        collect(Collectors.toList());
        /*}else if(indInicial > indFinal){ //VUELTA
            return this.paradas.
                    stream().
                    filter(parada -> this.isBetween(indFinal, indInicial, paradas.indexOf(parada))).
                    collect(Collectors.toList());
        }*/
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
        if(inicio < fin){ // ORDENADO (IDA)
            return (inicio <= valor) && (valor < fin); //NO QUIERO SABER LA DE FIN

        }
        else{ //AL REVES (VUELTA)
            return (fin < valor) && (valor <= inicio); // NO QUIERO SABER LA DE FIN
        }
    }


}

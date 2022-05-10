package domain.viaje.publico;

import java.util.ArrayList;
import java.util.Arrays;
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

    public boolean isBetween(int inicio, int fin, int valor){
        return (inicio <= valor) && (valor < fin);
    }


}

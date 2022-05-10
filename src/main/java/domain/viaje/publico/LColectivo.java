package domain.viaje.publico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LColectivo extends Linea {
    private String nombreLinea;
    private List<Parada> paradas;


    public LColectivo(String name) {
        this.nombreLinea = name;
        this.paradas = new ArrayList<>();
    }

    public void agregarParadas(Parada ... newParadas) {
        Collections.addAll(this.paradas, newParadas);
    }

    public List<Parada> getParadas(){
        return paradas;
    }
}

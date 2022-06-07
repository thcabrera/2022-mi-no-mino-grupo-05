package domain.viaje;

import domain.Direccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trayecto {
    private List<Trameable> tramos = new ArrayList<Trameable>();

    //  ----------  GETTERS & SETTERS  ----------

    public Trayecto(List<Trameable> tramos) {
        this.tramos = tramos;
    }

    //  ----------  PUNTOS DE INICIO/FIN  ----------

    // todo, surgen problemas con que los puntos de inicio/fin pueden ser tanto direcciones como paradas

    //  ----------  AGREGAR TRAMOS  ----------

    public void agregarTramos(Trameable ... tramos){
        Collections.addAll(this.tramos, tramos);
    }
}


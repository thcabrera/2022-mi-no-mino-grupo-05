package domain.viaje;

import domain.Direccion;
import domain.entidades.Organizacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trayecto {
    private List<Trameable> tramos = new ArrayList<Trameable>();



    // la organizacion es necesaria para saber a que organizacion le pertenece su consumo
    @Getter
    @Setter
    private Organizacion organizacion;

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

    public List<Trameable> getTramos(){
        return tramos;
    }

    //  ----------  CALCULO HC  ----------

    public Double calculoHC(){
        return tramos.stream().mapToDouble(tramo->tramo.calculoHC()).sum();
    }
}


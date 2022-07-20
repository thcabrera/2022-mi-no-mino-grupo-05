package domain.viaje.publico;

import domain.mediciones.consumos.Consumo;

import java.util.ArrayList;

public class LSubte extends Linea {
    public LSubte(String name, Double consumoPorKM) {
        this.nombreLinea = name;
        this.paradas = new ArrayList<>();
        this.consumo = consumoPorKM;
    }
}

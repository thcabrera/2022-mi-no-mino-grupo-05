package domain.viaje.publico;

import java.util.ArrayList;

public class LColectivo extends Linea {

    public LColectivo(String name, Double consumoPorKM) {
        this.nombreLinea = name;
        this.paradas = new ArrayList<>();
        this.consumo = consumoPorKM;
    }
}

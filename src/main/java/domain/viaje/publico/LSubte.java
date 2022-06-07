package domain.viaje.publico;

import java.util.ArrayList;

public class LSubte extends Linea {
    public LSubte(String name) {
        this.nombreLinea = name;
        this.paradas = new ArrayList<>();
    }
}

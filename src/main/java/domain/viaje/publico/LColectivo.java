package domain.viaje.publico;

import java.util.ArrayList;

public class LColectivo extends Linea {

    public LColectivo(String name) {
        this.nombreLinea = name;
        this.paradas = new ArrayList<>();
    }
}

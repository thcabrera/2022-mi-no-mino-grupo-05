package domain.viaje.publico;

import java.util.ArrayList;


public class LTren extends Linea {

    public LTren(String name) {
        this.nombreLinea = name;
        this.paradas = new ArrayList<>();
    }
}

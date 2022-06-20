package domain.viaje.publico;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


public class LTren extends Linea {

    public LTren(String name) {
        this.nombreLinea = name;
        this.paradas = new ArrayList<>();
    }
}

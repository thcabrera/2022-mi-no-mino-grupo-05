package domain.viaje.publico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LColectivo extends Linea {

    public LColectivo(String name) {
        this.nombreLinea = name;
        this.paradas = new ArrayList<>();
    }
}

package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;

public class CombustionFija extends ActividadConsumo {

    public CombustionFija(){}

    public CombustionFija(Periodicidad periodicidad, Consumo consumo) {
        super(periodicidad, consumo);
    }

}

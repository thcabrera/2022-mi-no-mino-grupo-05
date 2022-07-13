package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;

public class ElectricidadAdqYCons extends ActividadConsumo {

    public ElectricidadAdqYCons(){}

    public ElectricidadAdqYCons(Periodicidad periodicidad, Consumo consumo) {
        super(periodicidad, consumo);
    }

}

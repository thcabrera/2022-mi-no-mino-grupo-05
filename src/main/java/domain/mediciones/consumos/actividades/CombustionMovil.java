package domain.mediciones.consumos.actividades;
import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;

public class CombustionMovil extends ActividadConsumo {

    public CombustionMovil(){}

    public CombustionMovil(Periodicidad periodicidad, Consumo consumo) {
        super(periodicidad, consumo);
    }

}

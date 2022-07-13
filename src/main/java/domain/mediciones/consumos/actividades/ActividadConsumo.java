package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;

// Esta clase Combustion es para las actividades que tienen consumos con tipo variable
@Getter
@Setter
public class ActividadConsumo extends Actividad{

    private Consumo consumo;

    public ActividadConsumo(){

    }

    public ActividadConsumo(Periodicidad periodicidad, Consumo consumo) {
        super(periodicidad);
        this.consumo = consumo;
    }

    @Override
    public Double calculoHC(Periodicidad periodicidad) {
        return consumo.getValor() * factorEmision() * this.getPeriodicidad().obtenerPorcentaje(periodicidad);
    }

    @Override
    public Double factorEmision() {
        return consumo.valorFactorEmision();
    }
}

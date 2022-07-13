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
    private TipoActividadConsumo tipo;

    public ActividadConsumo(Periodicidad periodicidad, Consumo consumo, TipoActividadConsumo tipo) {
        super(periodicidad);
        this.tipo = tipo;
        this.consumo = consumo;
    }

    @Override
    public Double calculoHC(Periodicidad periodicidad) {
        return consumo.getValor() * consumo.valorFactorEmision() * this.getPeriodicidad().obtenerPorcentaje(periodicidad);
    }

}

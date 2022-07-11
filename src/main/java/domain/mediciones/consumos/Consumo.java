package domain.mediciones.consumos;

import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Consumo {

    private Double valor;

    private Periodicidad periodicidad;

    private TipoConsumo tipoConsumo;

    public Double getValor(Periodicidad periodo){
        if(coincidePeriodoImputacion(periodo)){

        }
    }

    public Boolean coincidePeriodoImputacion(Periodicidad periodoImputacion){
        // Si el periodo de la actividad coincide con el que me pasaron
        return periodoImputacion.coincide(this.periodicidad);
    }

    /*
    2 casos de periodicidad segun el parametro:

    Me pasan un Periodo anual
       Si la actividad es anual y coincide el año; la sumo
       Si la actividad es mensual y coincide el año; la sumo
       Si la actividad es mensual o anual y no coincide; no sumo
    Me pasan un Periodo mensual
       Si la actividad es mensual y coincide con el mes y año; se suma
       Si la actividad es anual y coincide el año; lo dividimos por 12 y se suma

    */

    public Double valorFactorEmision() {
        return this.tipoConsumo.valorFactorEmision();
    }
}

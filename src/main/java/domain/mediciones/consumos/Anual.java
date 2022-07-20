package domain.mediciones.consumos;

import lombok.Getter;

@Getter
public class Anual implements Periodicidad{
    private final int anio;

    public Anual(Integer anio) {
        this.anio = anio;
    }

    public Double obtenerPorcentaje(Periodicidad periodicidad){
        if (!coincide(periodicidad))
            return 0.0;
        return 1.0;
    }

    public boolean coincide(Periodicidad periodo){
        return this.anio == periodo.getAnio();
    }

/*
    Me pasan un Periodo anual
    Si la actividad es anual y coincide el año; la sumo
    Si la actividad es mensual y coincide el año; la sumo
    Si la actividad es mensual o anual y no coincide; no sumo
 */
}

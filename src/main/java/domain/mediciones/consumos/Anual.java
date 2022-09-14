package domain.mediciones.consumos;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "anual")
public class Anual extends Periodicidad{

    @Column(name = "anio")
    private final Integer anio;

    public Anual(Integer anio) {
        this.anio = anio;
    }


    public Integer getAnio(){
        return this.anio;
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

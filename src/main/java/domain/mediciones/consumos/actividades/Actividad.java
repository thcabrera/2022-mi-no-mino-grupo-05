package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Actividad {

    private Periodicidad periodicidad;

    public Actividad(){

    }
    public Actividad(Periodicidad periodicidad){
        this.periodicidad = periodicidad;
    }

    public abstract Double calculoHC(Periodicidad periodicidad);

    public abstract Double factorEmision();

}

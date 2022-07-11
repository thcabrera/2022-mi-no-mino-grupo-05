package domain.mediciones.consumos;

public class Anual extends Periodicidad{
    private Integer anio;

    public Anual(Integer anio) {
        this.anio = anio;
    }

    public void comportamiento(){
        //todo
    }

    public boolean coincide(Periodicidad periodo){
        return this.anio == periodo.getAnio();
    }

    public Integer getAnio(){
        return this.anio;
    }
/*
    Me pasan un Periodo anual
    Si la actividad es anual y coincide el año; la sumo
    Si la actividad es mensual y coincide el año; la sumo
    Si la actividad es mensual o anual y no coincide; no sumo
 */
}

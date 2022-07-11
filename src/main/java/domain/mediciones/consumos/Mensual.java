package domain.mediciones.consumos;

public class Mensual extends Periodicidad{
    private Integer mes;
    private Integer anio;

    public Mensual(Integer mes, Integer anio) {
        this.mes = mes;
        this.anio = anio;
    }

    public void comportamiento(){
        //todo
    }

    public boolean coincide(Anual periodo){
        return this.anio == periodo.getAnio();
    }

    public boolean coincide(Mensual periodo){
        return this.anio == periodo.getAnio() && this.mes == periodo.getMes();
    }

    private Integer getMes() {
        return this.mes;
    }

    public Integer getAnio(){
        return this.anio;
    }

/*    Me pasan un Periodo mensual
    Si la actividad es mensual y coincide con el mes y año; se suma
    Si la actividad es anual y coincide el año; lo dividimos por 12 y se suma*/
}

package domain.mediciones.consumos;

import domain.entidades.Municipio;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@NoArgsConstructor
@Getter
@Entity
@DiscriminatorValue(value = "mensual")
public class Mensual extends Periodicidad {

    @Column(name = "mes")
    private  Integer mes;

    @Column(name = "anio")
    private  Integer anio;

    public Mensual(int mes, int anio) {
        this.mes = mes;
        this.anio = anio;
    }

    public Double obtenerPorcentaje(Anual anual){
        if (!coincide(anual))
            return 0.0;
        return 1.0/12.0;
    }

    public Double obtenerPorcentaje(Mensual mensual){
        if (!coincide(mensual))
            return 0.0;
        return 1.0;
    }

    public Double obtenerPorcentaje(Periodicidad periodicidad){
        System.out.println("Entro a la clase mala :(");
        return -1.0;
    }

    public boolean coincide(Anual periodicidad){
        return this.anio == periodicidad.getAnio();
    }

    public boolean coincide(Mensual periodicidad){
        return this.anio == periodicidad.getAnio() && this.mes == periodicidad.getMes();
    }

    @Override
    public boolean coincide(Periodicidad periodicidad) {
        System.out.println("Entró a la clase mala :( f");
        return false;
    }
/*
    public class MensualDTO{

        public int id;
        public  Integer mes;
        public  Integer anio;


        public MensualDTO(Mensual mensual){
            this.id = mensual.getId();
            this.mes = mensual.mes;
            this.anio = mensual.anio;
        }
      }

        */

/*    Me pasan un Periodo mensual
    Si la actividad es mensual y coincide con el mes y año; se suma
    Si la actividad es anual y coincide el año; lo dividimos por 12 y se suma*/
}

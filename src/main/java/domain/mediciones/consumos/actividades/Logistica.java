package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.MedioTransporte;
import domain.mediciones.consumos.Periodicidad;
import domain.mediciones.consumos.tipoConsumo.ProductoTransportado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue(value="logistica")
public class Logistica extends Actividad {

    @Enumerated(EnumType.STRING)  //DUDA DE SI ESTA BIEN !!!
    private ProductoTransportado categoria; //MATERIA_PRIMA

    @OneToOne
    @JoinColumn(name = "medio_transporte_id", referencedColumnName = "id")
    private MedioTransporte medioTransporte; //MEDIO_TRANSPORTE	CAMION	MENSUAL	jun-22

    @Column(name = "distancia_media")
    private Double distanciaMedia;

    @Column(name = "peso")
    private Double peso;                      //PESO	800	MENSUAL	jun-22

    public Logistica(Periodicidad periodicidad, ProductoTransportado categoria,
                     MedioTransporte medioTransporte, Double distanciaMedia, Double peso) {
        super(periodicidad);
        this.categoria = categoria;
        this.medioTransporte = medioTransporte;
        this.distanciaMedia = distanciaMedia;
        this.peso = peso;
    }

    @Override
    public Double calculoHC(Periodicidad periodicidad) {
        return distanciaMedia * peso * medioTransporte.getFactorEmision() * this.getPeriodicidad().obtenerPorcentaje(periodicidad);
    }

    /*
    Logística de productos y
    residuos
    Categoría de producto
    transportado: materia prima,
            insumos, productos
    vendidos, residuos

-
    Distancia media recorrida km
    Peso total transportado kg*/

}

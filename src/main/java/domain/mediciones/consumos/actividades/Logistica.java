package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.MedioTransporte;
import domain.mediciones.consumos.Periodicidad;
import domain.mediciones.consumos.tipoConsumo.ProductoTransportado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Logistica extends Actividad {

    private ProductoTransportado categoria; //MATERIA_PRIMA
    private MedioTransporte medioTransporte; //MEDIO_TRANSPORTE	CAMION	MENSUAL	jun-22
    private Double distanciaMedia;
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
        return distanciaMedia * peso * factorEmision() * this.getPeriodicidad().obtenerPorcentaje(periodicidad); //TODO
    }

    @Override
    public Double factorEmision() {
        return medioTransporte.getFactorEmision();
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

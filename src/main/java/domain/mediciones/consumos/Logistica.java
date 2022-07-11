package domain.mediciones.consumos;

import domain.mediciones.consumos.actividades.Actividad;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Logistica extends Actividad {

    ProductoTransportado	categoria; //MATERIA_PRIMA
    medioTransporte  medioTransporte; //MEDIO_TRANSPORTE	CAMION	MENSUAL	jun-22
    Double distanciaMedia;
    Double peso;                      //PESO	800	MENSUAL	jun-22

    /*
    public Logistica(ProductoTransportado categoria, domain.mediciones.consumos.medioTransporte medioTransporte, Double distanciaMedia, Integer peso) {
        this.categoria = categoria;
        this.medioTransporte = medioTransporte;
        this.distanciaMedia = distanciaMedia;
        this.peso = peso;
    }
*/
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

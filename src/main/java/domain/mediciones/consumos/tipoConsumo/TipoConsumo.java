package domain.mediciones.consumos.tipoConsumo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoConsumo {

    private Unidad unidad;
    private Double factorEmision;

    public TipoConsumo(Unidad unidad, Double factorEmision){
        this.setUnidad(unidad);
        this.factorEmision = factorEmision;
    }

}

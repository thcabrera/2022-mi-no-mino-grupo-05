package domain.mediciones.consumos.tipoConsumo;

import domain.mediciones.consumos.FactorEmision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class TipoConsumo {

    private Unidad unidad;

    private Double factorEmision; //ex FactorEmision (no le vimos mucho)

    public TipoConsumo(Unidad unidad){
        this.setUnidad(unidad);
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    @Override
    public String toString() {
        return String.format(this.getClass().getSimpleName());
    }

    public Double valorFactorEmision() {
        return this.factorEmision;
    }
}

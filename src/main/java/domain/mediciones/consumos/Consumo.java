package domain.mediciones.consumos;

import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import lombok.Getter;
import lombok.Setter;

public class Consumo {

    @Getter
    @Setter
    private Double valor;

    @Getter
    @Setter
    private Periodicidad periodicidad;

    @Getter
    @Setter
    private TipoConsumo tipoConsumo;

    public Double valorFactorEmision() {
        return this.tipoConsumo.valorFactorEmision();
    }
}

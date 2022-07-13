package domain.mediciones.consumos;

import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Consumo {

    private final Double valor;
    private final TipoConsumo tipoConsumo;

    public Consumo(TipoConsumo tipo, Double valor) {
        this.tipoConsumo = tipo;
        this.valor = valor;
    }

    public Double valorFactorEmision() {
        return this.tipoConsumo.getFactorEmision();
    }
}

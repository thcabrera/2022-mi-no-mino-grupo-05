package domain.mediciones.consumos;

import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table
public class Consumo {

    @Id
    @GeneratedValue

    @Column(name = "valor")
    private final Double valor;

    @ManyToOne
    @JoinColumn(name="tipo_consumo_id", referencedColumnName = "id")
    private final TipoConsumo tipoConsumo;

    public Consumo(TipoConsumo tipo, Double valor) {
        this.tipoConsumo = tipo;
        this.valor = valor;
    }

    public Double valorFactorEmision() {
        return this.tipoConsumo.getFactorEmision();
    }
}

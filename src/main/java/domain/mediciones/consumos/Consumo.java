package domain.mediciones.consumos;

import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table
public class Consumo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "valor")
    private  Double valor;

    @ManyToOne
    @JoinColumn(name="tipo_consumo_id", referencedColumnName = "id")
    private  TipoConsumo tipoConsumo;

    public Consumo(TipoConsumo tipo, Double valor) {
        this.tipoConsumo = tipo;
        this.valor = valor;
    }

    public Double valorFactorEmision() {
        return this.tipoConsumo.getFactorEmision();
    }
}

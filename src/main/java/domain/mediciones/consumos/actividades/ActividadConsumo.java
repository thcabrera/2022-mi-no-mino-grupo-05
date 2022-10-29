package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Esta clase Combustion es para las actividades que tienen consumos con tipo variable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value="actividad_consumo")
public class ActividadConsumo extends Actividad{

    @OneToOne
    @JoinColumn(name = "consumo_id", referencedColumnName = "id")
    private Consumo consumo;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_actividad")
    private TipoActividadConsumo tipo;

    public ActividadConsumo(Periodicidad periodicidad, Consumo consumo, TipoActividadConsumo tipo) {
        super(periodicidad);
        this.tipo = tipo;
        this.consumo = consumo;
    }

    @Override
    public Double calculoHC(Periodicidad periodicidad) {
        return consumo.getValor() * consumo.valorFactorEmision() * this.getPeriodicidad().obtenerPorcentaje(periodicidad);
    }

}

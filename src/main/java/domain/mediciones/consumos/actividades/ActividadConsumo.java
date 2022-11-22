package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;
import lombok.*;

import javax.persistence.*;

// Esta clase Combustion es para las actividades que tienen consumos con tipo variable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value="actividad_consumo")
public class ActividadConsumo extends Actividad{

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "consumo_id", referencedColumnName = "id")
    private Consumo consumo;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_actividad")
    private TipoActividadConsumo tipo;

    public ActividadConsumo(Integer anio, Integer mes, Consumo consumo, TipoActividadConsumo tipo) {
        super(anio, mes);
        this.tipo = tipo;
        this.consumo = consumo;
    }

    @Override
    public Double calculoHC(Integer anio, Integer mes) {
        return consumo.getValor() * consumo.valorFactorEmision() * Periodicidad.obtenerPorcentaje(anio, mes, getAnio(), getMes());
    }

}

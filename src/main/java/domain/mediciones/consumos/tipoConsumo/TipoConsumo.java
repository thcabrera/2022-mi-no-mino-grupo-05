package domain.mediciones.consumos.tipoConsumo;

import domain.mediciones.consumos.actividades.TipoActividadConsumo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_consumo")
public class TipoConsumo {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name="unidad")
    private Unidad unidad;

    @Column(name = "factor_emision")
    private Double factorEmision;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad")
    private TipoActividadConsumo tipoActividad;

    public TipoConsumo(Unidad unidad, Double factorEmision){
        this.setUnidad(unidad);
        this.factorEmision = factorEmision;
    }

}

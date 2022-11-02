package domain.mediciones.consumos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "medio_transporte")
public class MedioTransporte {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "factor_emision")
    private Double factorEmision;

}

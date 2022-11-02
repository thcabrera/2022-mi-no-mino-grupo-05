package domain.mediciones.consumos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class MedioTransporte {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "factor_emision")
    private Double factorEmision;

    public MedioTransporte(Double factorEmision){
        this.factorEmision = factorEmision;
    }

}

package domain.mediciones.consumos;

import lombok.Getter;
import lombok.Setter;

public class MedioTransporte {

    @Getter
    @Setter
    private Double factorEmision;

    public MedioTransporte(Double factorEmision){
        this.factorEmision = factorEmision;
    }

}

package domain.viaje.privado.particular;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tipo_particular")
public class TipoParticular {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "consumo")
    private Double consumoPorKM;

    @Column(name = "descripcion")
    private String descripcion;

    public TipoParticular(Double consumoPorKM) {
        this.consumoPorKM = consumoPorKM;
    }

    public void setConsumoPorKM(Double nuevoConsumo){
        this.consumoPorKM = nuevoConsumo;
    }

}

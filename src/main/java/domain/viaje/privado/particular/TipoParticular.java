package domain.viaje.privado.particular;

import javax.persistence.*;

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

package domain.viaje.publico;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter

@Entity
@Table(name="parada")
public class Parada {
    private String nombre;

    @Column(name = "distancia_sig_parada")
    private Integer distanciaSigParada;

    @Column(name = "distancia_ant_parada")
    private Integer distanciaAntParada;


    public Parada(String nombreParada) {
        this.nombre = nombreParada;
    }
}

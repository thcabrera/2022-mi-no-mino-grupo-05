package domain.viaje.publico;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter

@Entity
@Table(name="parada")
public class Parada {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="nombre_parada")
    private String nombre;

    @Column(name = "distancia_sig_parada")
    private Integer distanciaSigParada;

    @Column(name = "distancia_ant_parada")
    private Integer distanciaAntParada;


    public Parada(String nombreParada) {
        this.nombre = nombreParada;
    }
}

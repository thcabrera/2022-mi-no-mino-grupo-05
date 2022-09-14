package domain.viaje.privado.particular;

import lombok.Getter;
import javax.persistence.*;

@Getter
@Entity
@Table(name = "combustible")
public class Combustible {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "consumo")
    private Double consumo; // El valor ronda entre 0.7, 1.1,

    @Column(name = "descripcion")
    private String descripcion;
}

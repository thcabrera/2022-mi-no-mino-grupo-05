package domain.viaje.publico;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name="tipo_linea")
public class TipoLinea {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="descripcion")
    private String nombre;

    @Column(name="consumo")
    private Double consumo;

}

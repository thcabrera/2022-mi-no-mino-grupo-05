package domain.entidades;

import javax.persistence.*;

@Entity
@Table(name="clasificacion")
public class Clasificacion {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="descripcion")
    private String nombre;
}

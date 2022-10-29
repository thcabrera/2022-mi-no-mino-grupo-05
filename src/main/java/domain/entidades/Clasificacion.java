package domain.entidades;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="clasificacion")
public class Clasificacion {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="descripcion")
    private String nombre;
}

package domain.usuarios;

import domain.entidades.Actor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Setter
@Getter
public class Usuario  {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre_usuario")
    private String nombreDeUsuario;

    @Column(name= "contrasenia")
    private String contrasenia;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actor_id", referencedColumnName = "id")
    private Actor actor;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;

}

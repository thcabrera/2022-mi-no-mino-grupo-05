package domain.usuarios;

import domain.entidades.Persona;
import domain.mediciones.consumos.MedioTransporte;
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

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona_id;

    @Column
    private String nombreDeUsuario;

    @Column
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private Rol rol;
}

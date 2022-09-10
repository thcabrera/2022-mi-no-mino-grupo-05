package domain.entidades.contacto;

import domain.entidades.Organizacion;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="contacto")
@DiscriminatorColumn(name="discriminador")
public abstract class Contacto {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "contacto")
    protected String contacto;

    @ManyToOne
    @JoinColumn(name="org_id", referencedColumnName = "id")
    private Organizacion organizacion;

    public abstract void notificar(Mensaje mensaje);
}

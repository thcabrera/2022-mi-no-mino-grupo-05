package domain.entidades.contacto;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
@Table(name="contacto")
public abstract class Contacto {

    @Id
    @GeneratedValue
    private Integer id;

    public void notificar(Mensaje mensaje){
        // todo
    }
}

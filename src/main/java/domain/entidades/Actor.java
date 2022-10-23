package domain.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

}

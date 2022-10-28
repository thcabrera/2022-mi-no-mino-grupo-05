package domain.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="tipo_org")
public class TipoOrg {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="descripcion")
    private String tipoOrg;

    public TipoOrg(String tipoOrg) {
        this.tipoOrg = tipoOrg;
    }
}

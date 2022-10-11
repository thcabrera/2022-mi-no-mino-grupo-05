package domain.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="tipo_org")
public class TipoOrg {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="descripcion")
    private String tipoOrg;

    //  ----------  GETTERS & SETTERS  ----------

    public String getTipoOrg() {
        return tipoOrg;
    }

    public TipoOrg(String tipoOrg) {
        this.tipoOrg = tipoOrg;
    }
}

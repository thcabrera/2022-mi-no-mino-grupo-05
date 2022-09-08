package domain.entidades;

import javax.persistence.*;

@Entity
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

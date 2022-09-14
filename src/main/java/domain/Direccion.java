package domain;

import domain.entidades.Localidad;
import domain.entidades.Municipio;
import domain.entidades.Provincia;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name="direccion")
public class Direccion {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private Integer altura;

    @ManyToOne
    @JoinColumn(name = "localidad_id", referencedColumnName = "id")
    private Localidad localidad;

    //  ----------  GETTERS & SETTERS  ----------

    public Direccion(String calle, Integer altura, Localidad localidad) {
        setCalle(calle);
        setAltura(altura);
        setLocalidad(localidad);
    }

    public Municipio getMunicipio(){
        return this.localidad.getMunicipio();
    }

    public Provincia getProvincia(){
        return this.localidad.getMunicipio().getProvincia();
    }

}

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

    @ManyToOne
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    private Provincia provincia;

    //  ----------  GETTERS & SETTERS  ----------

    public Direccion(){

    }

    public Direccion(String calle, Integer altura, Localidad localidad, Municipio municipio, Provincia provincia) {
        setCalle(calle);
        setAltura(altura);
        setLocalidad(localidad);
        setMunicipio(municipio);
        setProvincia(provincia);
    }

    public Direccion(String calle, Integer altura, Localidad localidad) {
        setCalle(calle);
        setAltura(altura);
        setLocalidad(localidad);
    }

    public String toString(){
        return String.format("%s %s", this.calle, this.altura);
    }

}

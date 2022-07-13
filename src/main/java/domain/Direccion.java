package domain;

import domain.entidades.Municipio;
import domain.entidades.Provincia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Direccion {

    private String calle;
    private Integer altura;
    private Provincia provincia;
    private Municipio municipio;
    private Integer localidad;

    //  ----------  GETTERS & SETTERS  ----------


    public Direccion(String calle, Integer altura, Provincia provincia, Municipio municipio, Integer localidad) {
        setCalle(calle);
        setAltura(altura);
        setProvincia(provincia);
        setMunicipio(municipio);
        setLocalidad(localidad);
    }
}

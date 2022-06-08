package domain;

import java.net.CookieHandler;

public class Direccion {
    public String calle;
    public Integer altura;
    private String provincia;
    private String municipio;
    public Integer localidad;

    //  ----------  GETTERS & SETTERS  ----------


    public Direccion(String calle, Integer altura, String provincia, String municipio, Integer localidad) {
        this.calle = calle;
        this.altura = altura;
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
    }
}

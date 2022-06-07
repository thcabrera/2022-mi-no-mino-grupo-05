package domain;

import java.net.CookieHandler;

public class Direccion {
    private String calle;
    private Integer altura;
    private String provincia;
    private String municipio;
    private String localidad;

    //  ----------  GETTERS & SETTERS  ----------


    public Direccion(String calle, Integer altura, String provincia, String municipio, String localidad) {
        this.calle = calle;
        this.altura = altura;
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
    }
}

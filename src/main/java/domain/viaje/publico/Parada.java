package domain.viaje.publico;

public class Parada {
    private String nombre;
    private Integer distanciaSigParada;

    public Integer getDistanciaSigParada() {
        return distanciaSigParada;
    }

    public Parada(String nombreParada) {
        this.nombre = nombreParada;
    }
}

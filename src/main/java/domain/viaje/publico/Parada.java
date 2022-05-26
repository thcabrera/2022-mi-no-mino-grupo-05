package domain.viaje.publico;

public class Parada {
    private String nombre;
    private Integer distanciaSigParada;

    public Parada(String nombreParada) {
        this.nombre = nombreParada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDistanciaSigParada() {
        return this.distanciaSigParada;
    }

    public void setDistanciaSigParada(int distanciaSigParada) {
        this.distanciaSigParada = distanciaSigParada;
    }
}

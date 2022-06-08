package domain.mediciones.consumos;

public class TipoConsumo {

    private Unidad unidad;
    private String nombre;

    public TipoConsumo(String nombre, Unidad unidad){
        this.setNombre(nombre);
        this.setUnidad(unidad);
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return String.format(this.getNombre());
    }
}

package domain.mediciones.consumos;

public interface Periodicidad {

    boolean coincide(Periodicidad periodicidad);

    int getAnio();

    Double obtenerPorcentaje(Periodicidad periodicidad);

}
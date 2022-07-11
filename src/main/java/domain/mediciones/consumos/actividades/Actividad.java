package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;

public abstract class Actividad {
    private Consumo consumo;

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    @Override
    public String toString() {
        return String.format("Actividad %s:\nConsumo:\n\tTipo: %s\n\tValor: %.2f\nPeriodicidad: %s",
                this.getClass().getSimpleName(),
                this.getConsumo().getTipoConsumo(),
                this.getConsumo().getValor(),
                this.getConsumo().getPeriodicidad());
    }

    public Double calculoHC(Periodicidad periodicidad) {
        return this.datoActividad(periodicidad) * this.factorEmision();
    }

    public Double factorEmision() {
        return consumo.valorFactorEmision();
    }

    public Double datoActividad(Periodicidad periodicidad) {
        return this.consumo.getValor(periodicidad);
    }



}

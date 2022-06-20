package domain.mediciones.consumos.actividades;

import domain.mediciones.consumos.Consumo;

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

    public Double calculoHC() {
        return this.datoActividad() * this.factorEmision();
    }

    public Double factorEmision() {
        return consumo.valorFactorEmision();
    }

    public Double datoActividad() {
        return this.consumo.getValor();
    }

}

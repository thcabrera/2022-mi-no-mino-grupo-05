package domain.mediciones.consumos;

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
        return String.format("Actividad %s:\nConsumo:\n\tTipo: %s\n\tValor: %d\nPeriodicidad: %s",
                this.getClass().getSimpleName(),
                this.getConsumo().getTipoConsumo(),
                this.getConsumo().getValor(),
                this.getConsumo().getPeriodicidad());
    }
}

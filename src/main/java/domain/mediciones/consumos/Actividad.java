package domain.mediciones.consumos;

public abstract class Actividad {
    private Consumo consumo;

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }
}

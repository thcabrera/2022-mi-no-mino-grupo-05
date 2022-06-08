package domain.mediciones.consumos;

public abstract class Periodicidad { // Seria interfaz :D
    protected String periodo;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return String.format("%s %s",
                this.getClass().getSimpleName(),
                this.periodo);
    }
}

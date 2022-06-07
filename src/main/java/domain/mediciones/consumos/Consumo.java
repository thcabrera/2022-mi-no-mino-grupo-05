package domain.mediciones.consumos;

public class Consumo {
    private Integer valor;
    private Periodicidad periodicidad;
    private TipoConsumo tipoConsumo;

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public TipoConsumo getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(TipoConsumo tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }
}

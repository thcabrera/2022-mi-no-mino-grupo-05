package domain.viaje.privado.particular;

public abstract class TipoParticular {
    protected Double consumoPorKM;

    protected Combustible tipoCombustible;

    public TipoParticular(Double consumoPorKM, Combustible tipoCombustible) {
        this.consumoPorKM = consumoPorKM;
        this.tipoCombustible = tipoCombustible;
    }

    public Double getConsumoPorKM(){
        return consumoPorKM * this.consumoCombustible();
    }

    public void setConsumoPorKM(Double nuevoConsumo){
        this.consumoPorKM = nuevoConsumo;
    }

    protected Double consumoCombustible(){
        return this.tipoCombustible.getConsumo();
    }
}

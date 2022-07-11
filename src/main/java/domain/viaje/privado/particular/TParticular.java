package domain.viaje.privado.particular;

import domain.Direccion;
import domain.entidades.Persona;
import domain.services.calculoDistancia.ServicioDistancia;
import domain.services.calculoDistancia.adapters.ServicioDistanciaRetrofitAdapter;
import domain.services.calculoDistancia.entities.Distancia;
import domain.viaje.privado.TPrivado;
import lombok.SneakyThrows;

public class TParticular extends TPrivado {
    private Combustible tipoCombustible;
    private TipoParticular tipoParticular;
    private Direccion direccionInicio;
    private Direccion direccionFin;
    private Boolean esCompartido;
    private Persona propietario;

    //  ----------  GETTERS & SETTERS  ----------

    public TParticular(Combustible tipoCombustible, /*TipoParticular tipoParticular,*/Direccion direccionInicio, Direccion direccionFin, Boolean esCompartido) {
        this.tipoCombustible = tipoCombustible;
//        this.tipoParticular = tipoParticular;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
        this.esCompartido = esCompartido;
        this.propietario = null;
    }

    @Override
    public Double calculoHC(Persona persona){
        if(this.propietario == persona){
            return this.consumoPorKM() * this.calcularDistanciaTramo();
        }
        return 0.0;
    }

    @Override
    public Persona getPropietario() {
        return propietario;
    }

    public boolean getEsCompartido(){
        return esCompartido;
    }

    //  ----------  CONSUMO  ----------
    public Double consumoPorKM(){
        return tipoParticular.getConsumoPorKM() * this.calcularDistanciaTramo();
    }

    //  ----------  CALCULO DE DISTANCIA  ----------
//    @Override
//    public Double calcularDistanciaTramo() {
//        return this.distanciaTramo(new ServicioDistancia()).valor;
//    }
//
//    @SneakyThrows //????????????????
//    public Distancia distanciaTramo(ServicioDistancia servicio){
//        return servicio.calcularDistanciaTramo(this.direccionInicio, this.direccionFin);
//    }
}

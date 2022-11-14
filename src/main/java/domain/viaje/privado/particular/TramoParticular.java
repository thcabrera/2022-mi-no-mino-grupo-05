package domain.viaje.privado.particular;

import domain.Direccion;
import domain.entidades.Persona;
import domain.services.calculoDistancia.ServicioDistancia;
import domain.services.calculoDistancia.entities.Distancia;
import domain.viaje.Tramo;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.persistence.*;

@Getter
@Entity
@Table(name="tramo_particular")
public class TramoParticular extends Tramo {

    @ManyToOne
    @JoinColumn(name = "tipo_combustible_id", referencedColumnName = "id")
    private Combustible tipoCombustible;

    @ManyToOne
    @JoinColumn(name = "tipo_particular_id", referencedColumnName = "id")
    private TipoParticular tipoParticular;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_inicio_id", referencedColumnName = "id")
    private Direccion direccionInicio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_fin_id", referencedColumnName = "id")
    private Direccion direccionFin;

    @Column(name = "es_compartido")
    private Boolean esCompartido;

    @ManyToOne
    @JoinColumn(name = "propietario_id", referencedColumnName = "id")
    private Persona propietario;

    //  ----------  GETTERS & SETTERS  ----------
    public TramoParticular(){}
    public TramoParticular(Combustible tipoCombustible, TipoParticular tipoParticular, Direccion direccionInicio, Direccion direccionFin, Boolean esCompartido) {
        this.tipoCombustible = tipoCombustible;
        this.tipoParticular = tipoParticular;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
        this.esCompartido = esCompartido;
        this.propietario = null;
    }

    public TramoParticular(Combustible tipoCombustible, Direccion direccionInicio, Direccion direccionFin, Boolean esCompartido) {
        this.tipoCombustible = tipoCombustible;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
        this.esCompartido = esCompartido;
        this.propietario = null;
    }

    public TramoParticular(Combustible tipoCombustible, TipoParticular tipoParticular,
                           Direccion direccionInicio, Direccion direccionFin, Boolean esCompartido,
                           Persona persona) {
        this.tipoCombustible = tipoCombustible;
        this.tipoParticular = tipoParticular;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
        this.esCompartido = esCompartido;
        this.propietario = persona;
        setDistancia();
    }

    @Override
    public Double calculoHC(Persona persona) {
        if (this.propietario == persona) {
            return this.consumoPorKM() * this.getDistancia();
        }
        return 0.0;
    }

    @Override
    public String obtenerInicio() {
        return this.direccionInicio.toString();
    }

    @Override
    public String obtenerFin() {
        return this.direccionFin.toString();
    }

    @Override
    public String obtenerTipo() {
        return this.tipoParticular.getDescripcion();
    }

    @Override
    public Persona getPropietario() {
        return propietario;
    }

    @Override
    public boolean getEsCompartido() {
        return esCompartido;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    //  ----------  CONSUMO  ----------
    @Override
    public Double consumoPorKM() {
        return this.tipoCombustible.getConsumo();
    }

    @Override
    public Double calcularDistanciaTramo() {
        return this.distanciaTramo(new ServicioDistancia()).valor;
    }

    @SneakyThrows
    public Distancia distanciaTramo(ServicioDistancia servicio) {
        return servicio.calcularDistanciaTramo(this.direccionInicio, this.direccionFin);
    }
}

package domain.viaje.privado.contratado;

import domain.Direccion;
import domain.entidades.Persona;
import domain.viaje.privado.TPrivado;
import javax.persistence.*;

@Entity
@Table(name="tramo_contratado")
public class TContratado extends TPrivado {

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", referencedColumnName = "id")
    private Servicio tipoTransporte;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_inicio_id", referencedColumnName = "id")
    private Direccion direccionInicio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_fin_id", referencedColumnName = "id")
    private Direccion direccionFin;

    @ManyToOne
    @JoinColumn(name = "propietario_id", referencedColumnName = "id")
    private Persona propietario;

    @Column(name = "es_compartido")
    private Boolean esCompartido;

    @Column(name = "consumo")
    private Double consumoPorKM;

    //  ----------  GETTERS & SETTERS  ----------
    public TContratado(Servicio tipoTransporte, Direccion direccionInicio, Direccion direccionFin, boolean esCompartido) {
        this.tipoTransporte = tipoTransporte;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
        this.esCompartido = esCompartido;
        this.propietario = null;
    }
    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    @Override
    public Double calculoHC(Persona persona){
        if (this.esElPropietario(persona)){
            return this.consumoPorKM() * this.calcularDistanciaTramo();
        }
        else
            return 0.0;
    }

    public Boolean esElPropietario(Persona persona){
        return this.propietario == persona;
    }

    @Override
    public Persona getPropietario() {
        return propietario;
    }

    @Override
    public Double consumoPorKM() {
        return this.consumoPorKM;
    }

    public boolean getEsCompartido(){
        return esCompartido;
    }
}
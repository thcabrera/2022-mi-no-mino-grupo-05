package domain.viaje.privado.limpio;

import domain.Direccion;
import domain.entidades.Persona;
import domain.viaje.privado.TPrivado;

import javax.persistence.*;

@Entity
@Table(name="tramo_limpio")
public class TLimpio extends TPrivado {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="tipo")
    public String tipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_inicio_id", referencedColumnName = "id")
    private Direccion direccionInicio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_fin_id", referencedColumnName = "id")
    private Direccion direccionFin;

    //  ----------  GETTERS & SETTERS  ----------

    public TLimpio(String tipo, Direccion direccionInicio, Direccion direccionFin) {
        this.tipo = tipo;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
    }

    public boolean getEsCompartido(){
        return false;
    }
    @Override
    public Persona getPropietario() {
        return null;
    }

    //  ----------  CONSUMO  ----------
    @Override
    public Double calculoHC(Persona persona){
        return 0.0;
    }
    @Override
    public Double consumoPorKM(){
        return 0.0;
    }

}

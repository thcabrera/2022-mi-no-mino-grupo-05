package domain.viaje;

import domain.entidades.Organizacion;
import domain.entidades.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name="trayecto")
public class Trayecto {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="tramo_trayecto",
            joinColumns = @JoinColumn(name = "trayecto_id"),
            inverseJoinColumns = @JoinColumn(name = "tramo_id")
    )
    private List<Tramo> tramos = new ArrayList<Tramo>();

    @ManyToOne()
    @JoinColumn(name="org_id", referencedColumnName = "id")
    private Organizacion organizacion;

    @ManyToOne
    @JoinColumn(name="persona_id", referencedColumnName = "id")
    private Persona persona;


    public Trayecto(){

    }

    public Trayecto(List<Tramo> tramos) {
        this.tramos = tramos;
    }

    //  ----------  AGREGAR TRAMOS  ----------

    public void agregarTramos(Tramo... tramos){
        Collections.addAll(this.tramos, tramos);
    }

    public List<Tramo> getTramos(){
        return tramos;
    }

    //  ----------  CALCULO HC  ----------


    public Double calculoHC(Persona persona) {
        return tramos.stream().mapToDouble(tramo -> tramo.calculoHC(persona)).sum();
    }
}


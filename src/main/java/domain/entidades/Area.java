package domain.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="area")
public class Area {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organizacion organizacion;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="area_persona",
            joinColumns = @JoinColumn(name = "area_id")
    )
    private List<Persona> miembros;

    @Column(name="descripcion")
    private String nombre;

    //  ----------  GETTERS & SETTERS  ----------

    public Area(String nombre, Organizacion organizacion) {
        this.organizacion = organizacion;
        this.nombre = nombre;
        this.miembros = new ArrayList<Persona>();
        organizacion.agregarArea(this);
    }
    public List<Persona> getMiembros(){
        return miembros;
    }
    //  ----------  AGREGAR MIEMBROS  ----------
    public void agregarMiembro(Persona persona){
        miembros.add(persona);
    }

    //  ----------  CALCULO HC  ----------
    public Double calculoHC(){
        return this.miembros.stream().mapToDouble(p -> p.calcularHC(this.organizacion)).sum();
    }

    public Double indicadorHCporMiembro(){
        return this.calculoHC() / this.getMiembros().size();
    }


}
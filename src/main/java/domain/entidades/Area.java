package domain.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
@ToString
@Getter
@Setter
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
            joinColumns = @JoinColumn(name = "area_id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id")
    )
    @ToString.Exclude
    private List<Persona> miembros;
        
    @Column(name="descripcion")
    private String nombre;

    //  ----------  GETTERS & SETTERS  ----------

    public Area(){

    }

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
        return this.miembros.stream().mapToDouble(p -> p.calculoHC(this.organizacion)).sum();
    }
    public Double impactoHCEnSuOrg(){
        return this.calculoHC() / this.organizacion.calculoHCTrayectos() * 100;
    }
    public Double indicadorHCporMiembro(){
        return this.calculoHC() / this.getMiembros().size();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public void setMiembros(List<Persona> miembros) {
        this.miembros = miembros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AreaDTO convertirADTO(){
        return new AreaDTO(this);
    }
    public ReporteAreaDTO convertirAReporteAreaDTO(){
        return new ReporteAreaDTO(this);
    }
    @Getter
    @Setter
    public class AreaDTO{

        public String id;
        public String nombre;

        public AreaDTO(Area area){
            this.id = String.valueOf(area.getId());
            this.nombre = area.getNombre();
        }
    }
    @Getter
    @Setter
    public class ReporteAreaDTO{
        public String id;
        public String nombre;
        public String calculoHC;
        public String impactoHCEnSuOrg;
        public String indicadorHCporMiembro;

        public ReporteAreaDTO(Area area){

            this.id = String.valueOf(area.getId());
            this.nombre = area.getNombre();
            this.calculoHC = new DecimalFormat("#.##").format(area.calculoHC());
            this.impactoHCEnSuOrg = new DecimalFormat("#.##").format(area.impactoHCEnSuOrg());
            this.indicadorHCporMiembro = new DecimalFormat("#.##").format(area.indicadorHCporMiembro());
        }
    }

}
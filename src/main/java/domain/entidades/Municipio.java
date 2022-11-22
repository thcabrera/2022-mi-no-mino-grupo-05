package domain.entidades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

// un municipio tiene varias organizaciones
// no le interesa al municipio saber de que Provincia es
@Getter
@Entity
@DiscriminatorValue("MUNICIPIO")
public class Municipio extends Sector{

    @Setter
    @Transient
    private ArrayList<Organizacion> organizaciones;

    @ManyToOne
    @JoinColumn(name="provincia_id", referencedColumnName = "id")
    private Provincia provincia;

    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
    private Set<Localidad> localidades;

    // ----------------------- CONSTRUCTORES PARA LOS TESTS -------------------------- //

    public Municipio(){

    }
    public Municipio(Provincia provincia){
        this.provincia = provincia;
    }

    public Municipio(ArrayList<Organizacion> organizaciones){
        this.organizaciones = organizaciones;
    }

    // --------------------------------------------------------------------------

    @Override
    public Double calculoHC(Integer anio, Integer mes) {
        return
                this.getOrganizaciones().stream().mapToDouble(org->org.calculoHC(anio, mes)).sum();
    }

    public MunicipioDTO convertirADTO(){
        return new MunicipioDTO(this);
    }

    public class MunicipioDTO{

        public int id;
        public String descripcion;

        public MunicipioDTO(Municipio municipio){
            this.id = municipio.id;
            this.descripcion = municipio.descripcion;
        }

    }

}

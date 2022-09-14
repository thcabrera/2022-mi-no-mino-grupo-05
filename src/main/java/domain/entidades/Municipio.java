package domain.entidades;

import domain.db.EntityManagerHelper;
import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// un municipio tiene varias organizaciones
// no le interesa al municipio saber de que Provincia es
@Getter
@Entity
@Table(name = "municipio")
public class Municipio extends Sector{

    @Setter
    @Transient
    private ArrayList<Organizacion> organizaciones;

    @ManyToOne
    @JoinColumn(name="provincia", referencedColumnName = "id")
    private Provincia provincia;

    // ----------------------- CONSTRUCTORES PARA LOS TESTS -------------------------- //

    public Municipio(){

    }

    public Municipio(ArrayList<Organizacion> organizaciones){
        this.organizaciones = organizaciones;
    }

    // --------------------------------------------------------------------------

    public List<Organizacion> obtenerOrganizaciones(){
        List<Object> organizaciones = EntityManagerHelper.createQuery("from Organizacion o join Direccion d on o.direccion_id = d.id where d.municipio_id = '" + this.id.toString() + "'").getResultList();
        return organizaciones.stream().map(o -> (Organizacion) o).collect(Collectors.toList());
    }
    
    @Override
    public Double calculoHC(Periodicidad periodo) {
        return this.getOrganizaciones().stream().mapToDouble(org->org.calculoHC(periodo)).sum();
    }
}

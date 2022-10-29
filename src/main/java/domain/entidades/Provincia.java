package domain.entidades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("PROVINCIA")
// una provincia tiene varios municipios
public class Provincia extends Sector{

    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private Set<Municipio> municipios;

    public Provincia(){

    }

    public Provincia(Set<Municipio> municipios){
        setMunicipios(municipios);
    }

    @Override
    public Double calculoHC(Periodicidad periodo) {
        return this.getMunicipios().stream().mapToDouble(mun->mun.calculoHC(periodo)).sum();
    }

    public ProvinciaDTO convertirADTO(){
        return new ProvinciaDTO(this);
    }

    @Getter
    public class ProvinciaDTO{
        public int id;
        public String descripcion;
        public ProvinciaDTO(Provincia provincia){
            this.id = provincia.id;
            this.descripcion = provincia.descripcion;
        }
    }

}

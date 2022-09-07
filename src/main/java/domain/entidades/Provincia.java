package domain.entidades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "provincia")
// una provincia tiene varios municipios
public class Provincia extends Sector{

    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private ArrayList<Municipio> municipios;

    public Provincia(ArrayList<Municipio> municipios){
        setMunicipios(municipios);
    }

    @Override
    public Double calculoHC(Periodicidad periodo) {
        return this.getMunicipios().stream().mapToDouble(mun->mun.calculoHC(periodo)).sum();
    }
}

package domain.entidades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

// una provincia tiene varios municipios
public class Provincia extends Sector{

    @Getter
    @Setter
    private ArrayList<Municipio> municipios;

    public Provincia(ArrayList<Municipio> municipios){
        setMunicipios(municipios);
    }

    @Override
    public Double calculoHC(Periodicidad periodo) {
        return this.getMunicipios().stream().mapToDouble(mun->mun.calculoHC(periodo)).sum();
    }
}

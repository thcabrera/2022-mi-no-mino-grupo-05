package domain.entidades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

// un municipio tiene varias organizaciones
// no le interesa al municipio saber de que Provincia es
public class Municipio extends Sector{

    @Getter
    @Setter
    private ArrayList<Organizacion> organizaciones;

    public Municipio(ArrayList<Organizacion> organizaciones){
        setOrganizaciones(organizaciones);
    }

    @Override
    public Double calculoHC(Periodicidad periodo) {
        return this.getOrganizaciones().stream().mapToDouble(org->org.calculoHC(periodo)).sum();
    }
}

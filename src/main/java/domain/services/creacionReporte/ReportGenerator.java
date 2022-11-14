package domain.services.creacionReporte;

import domain.entidades.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Setter
public class ReportGenerator{

    public void HCTotalPorSectorTerritorial() {

    }

    public void HCTotalPorTipoDeOrg() {

    }

    @SneakyThrows
    public void composicionDeHCTotal(List<Provincia> provincias) {

//        StringBuilder exportText = new StringBuilder();
//        for (Provincia provincia: provincias){
//            exportText.append("Composicion de ").append(provincia.getDescripcion()).append(":\n");
//            for (Municipio m: provincia.getMunicipios()){
//                exportText.append("Composicion de ").append(m.getDescripcion()).append(":\n");
//            }
//            String line = "Fecha: " + huella.getFechaMedicion().toString() + "; Valor: " + huella.getValor() + ";";
//            exportText.append(line).append("\n");
//
//        }
//        FileWriter file = new FileWriter("COMPOSICION_TOTAL_POR_PROVINCIAS.txt");
//        file.write(exportText.toString());
//        file.close();

    }

    public void composicionDeHCTotal(Sector sector) {

    }

    public void composicionDeHCTotal(Organizacion organizacion) {

    }

    public void evolucionDeHCTotal(Sector sector) {

    }

    @SneakyThrows
    public void evolucionDeHCTotal(Organizacion organizacion) {
        StringBuilder exportText = new StringBuilder();
        exportText.append("Evolucion de la organizacion ").append(organizacion.getRazonSocial()).append(":\n");
        for(HuellaDeCarbono huella: organizacion.getHuellasDeCarbono()){
            String line = "Fecha: " + huella.getFechaMedicion().toString() + "; Valor: " + huella.getValor() + ";";
            exportText.append(line).append("\n");
        }
        FileWriter file = new FileWriter("src/main/resources/reportes/"
                + organizacion.getRazonSocial() + "_evolucion.txt");
        file.write(exportText.toString());
        file.close();
    }
}

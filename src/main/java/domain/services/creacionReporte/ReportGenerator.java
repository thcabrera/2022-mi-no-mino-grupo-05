package domain.services.creacionReporte;

import domain.entidades.HuellaDeCarbono;
import domain.entidades.Organizacion;
import domain.entidades.Sector;
import lombok.SneakyThrows;
import java.io.FileWriter;

public class ReportGenerator{

    public void HCTotalPorSectorTerritorial() {

    }

    public void HCTotalPorTipoDeOrg() {

    }

    public void composicionDeHCTotal() {

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
        FileWriter file = new FileWriter(organizacion.getRazonSocial() + "_evolucion" + ".txt");
        file.write(exportText.toString());
        file.close();
    }


}

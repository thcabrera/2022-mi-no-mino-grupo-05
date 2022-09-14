package domain.services.crearReporte;

import domain.entidades.Organizacion;
import domain.entidades.Sector;

public interface ReportGeneratorAdapter {

    void HCTotalPorSectorTerritorial();

    void HCTotalPorTipoDeOrg();


    // Composición de HC total a nivel país (discriminando provincias)
    void composicionDeHCTotal();

    // Composición de HC total de un determinado sector territorial
    void composicionDeHCTotal(Sector sector);

    // Composición de HC total de una determinada Organización
    void composicionDeHCTotal(Organizacion organizacion);

    // Evolución de HC total de un determinado sector territorial
    void evolucionDeHCTotal(Sector sector);

    // Evolución de HC total de una determinada Organización
    void evolucionDeHCTotal(Organizacion organizacion);

}

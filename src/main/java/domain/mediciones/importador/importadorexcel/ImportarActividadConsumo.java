package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;
import domain.mediciones.consumos.actividades.*;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;

@Setter
public class ImportarActividadConsumo {

    private ImportarConsumo importarConsumo;
    private ImportarTipoConsumoFijo importarTipoConsumoFijo;
    private ImportarTipoConsumoMovil importarTipoConsumoMovil;
    private ImportarTipoConsumoElectricidad importarTipoConsumoElectricidad;
    private ImportarPeriodicidad importarPeriodicidad;
    public ImportarActividadConsumo(ImportarPeriodicidad importarPeriodicidad,
                                    ImportarConsumo importarConsumo,
                                    ImportarTipoConsumoFijo importarTipoConsumoFijo,
                                    ImportarTipoConsumoMovil importarTipoConsumoMovil,
                                    ImportarTipoConsumoElectricidad importarTipoConsumoElectricidad){
        setImportarConsumo(importarConsumo);
        setImportarPeriodicidad(importarPeriodicidad);
        setImportarTipoConsumoFijo(importarTipoConsumoFijo);
        setImportarTipoConsumoMovil(importarTipoConsumoMovil);
        setImportarTipoConsumoElectricidad(importarTipoConsumoElectricidad);
    }
    public ActividadConsumo importar(Row fila){
        Iterator<Cell> cellIterator = fila.cellIterator();
        Cell tipoActividad = cellIterator.next();
        TipoActividadConsumo tipo;
        switch(tipoActividad.getStringCellValue()) {
            case "COMBUSTIÓN FIJA":
                importarConsumo.setImportarTipo(importarTipoConsumoFijo);
                tipo = TipoActividadConsumo.COMBUSTION_FIJA;
                break;
            case "COMBUSTIÓN MÓVIL":
                importarConsumo.setImportarTipo(importarTipoConsumoMovil);
                tipo = TipoActividadConsumo.COMBUSTION_MOVIL;
                break;
            case "ELECTRICIDAD":
                importarConsumo.setImportarTipo(importarTipoConsumoElectricidad);
                tipo = TipoActividadConsumo.ELECTRICIDAD;
                break;
            default:
                return null;
        }
        Consumo consumo = importarConsumo.importar(cellIterator);
        Periodicidad periodicidad = importarPeriodicidad.importar(cellIterator);
        return new ActividadConsumo(periodicidad, consumo, tipo);
    }

}

package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.actividades.*;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import java.util.Iterator;

@Setter
public class ImportarActividadConsumo {

    private ImportarConsumo importarConsumo;
    private ImportarTipoConsumoFijo importarTipoConsumoFijo;
    private ImportarTipoConsumoMovil importarTipoConsumoMovil;
    private ImportarTipoConsumoElectricidad importarTipoConsumoElectricidad;
    private ImportarPeriodicidad importarPeriodicidad;
    public ImportarActividadConsumo(ImportarConsumo importarConsumo){
        setImportarConsumo(importarConsumo);
    }
    public ActividadConsumo importar(Iterator<Cell> cellIterator){
        ActividadConsumo actividad;
        Cell tipoActividad = cellIterator.next();
        switch(tipoActividad.getStringCellValue()){
            case "COMBUSTIÓN FIJA":
                actividad = new CombustionFija();
                importarConsumo.setImportarTipo(importarTipoConsumoFijo);
                break;
            case "COMBUSTIÓN MÓVIL":
                actividad = new CombustionMovil();
                importarConsumo.setImportarTipo(importarTipoConsumoMovil);
                break;
            case "ELECTRICIDAD":
                actividad = new ElectricidadAdqYCons();
                importarConsumo.setImportarTipo(importarTipoConsumoElectricidad);
                break;
            default:
                return null;
        }
        actividad.setConsumo(importarConsumo.importar(cellIterator));
        actividad.setPeriodicidad(importarPeriodicidad.importar(cellIterator.next()));
        return actividad;
    }

}

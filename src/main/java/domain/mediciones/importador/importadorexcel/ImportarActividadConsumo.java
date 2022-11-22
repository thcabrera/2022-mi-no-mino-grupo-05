package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;
import domain.mediciones.consumos.actividades.*;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.xml.bind.ValidationException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

@Setter
public class ImportarActividadConsumo {

    private ImportarConsumo importarConsumo;
    private ImportarTipoConsumo importarTipoConsumoFijo;
    private ImportarTipoConsumo importarTipoConsumoMovil;
    private ImportarTipoConsumo importarTipoConsumoElectricidad;
    public ImportarActividadConsumo(ImportarConsumo importarConsumo,
                                    ImportarTipoConsumo importarTipoConsumoFijo,
                                    ImportarTipoConsumo importarTipoConsumoMovil,
                                    ImportarTipoConsumo importarTipoConsumoElectricidad){
        setImportarConsumo(importarConsumo);
        setImportarTipoConsumoFijo(importarTipoConsumoFijo);
        setImportarTipoConsumoMovil(importarTipoConsumoMovil);
        setImportarTipoConsumoElectricidad(importarTipoConsumoElectricidad);
    }
    @SneakyThrows
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
        Integer anio = null;
        Integer mes = null;
        switch(cellIterator.next().getStringCellValue()){
            case "Anual":
                anio = (int) cellIterator.next().getNumericCellValue();
                break;
            case "Mensual":
                Date date = cellIterator.next().getDateCellValue();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(date);
                anio = cal.get(Calendar.YEAR);
                mes = cal.get(Calendar.MONTH) + 1;
                break;
            default:
                throw new ValidationException("EL FORMATO ESPECIFICADO NO ES CORRECTO!");
        }
        return new ActividadConsumo(anio, mes, consumo, tipo);
    }

}

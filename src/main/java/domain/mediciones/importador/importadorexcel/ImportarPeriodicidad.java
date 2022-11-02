package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.Anual;
import domain.mediciones.consumos.Mensual;
import domain.mediciones.consumos.Periodicidad;
import org.apache.poi.ss.usermodel.Cell;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

public class ImportarPeriodicidad {

    public Periodicidad importar(Iterator<Cell> iterador){
        switch (iterador.next().getStringCellValue()){
            case "Anual":
                return new Anual((int) iterador.next().getNumericCellValue());
            case "Mensual":
                Date date = iterador.next().getDateCellValue();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                return new Mensual(month, year);
            default:
                return null;
        }
    }

}

package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.Mensual;
import domain.mediciones.consumos.Periodicidad;
import org.apache.poi.ss.usermodel.Cell;

public class ImportarPeriodicidad {

    public Periodicidad importar(Cell celda){
        return new Mensual(4,2020);
    }

}

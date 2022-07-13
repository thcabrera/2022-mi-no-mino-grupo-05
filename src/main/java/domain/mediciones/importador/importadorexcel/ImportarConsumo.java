package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import java.util.Iterator;

public class ImportarConsumo {

    @Setter
    private ImportarTipoConsumo importarTipo;

    public Consumo importar(Iterator<Cell> cellIterator){
        Cell celdaTipo = cellIterator.next();
        TipoConsumo tipo = importarTipo.importar(celdaTipo.getStringCellValue());
        Cell celdaValor = cellIterator.next();
        Double valor = celdaValor.getNumericCellValue();
        return new Consumo(tipo, valor);
    }

}

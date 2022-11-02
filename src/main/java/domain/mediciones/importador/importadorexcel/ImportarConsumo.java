package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;

public class ImportarConsumo {

    @Setter
    private ImportarTipoConsumo importarTipo;

    public Consumo importar(Iterator<Cell> cellIterator){
        Cell celdaTipo = cellIterator.next();
        TipoConsumo tipo = importarTipo.importar(celdaTipo.getStringCellValue());
        if (tipo == null)
            throw new IllegalArgumentException(
                    String.format("El excel no cuenta con el formato especificado.\n" +
                                    "Error causado por Tipo de Consumo: %s\n",
                    celdaTipo.getStringCellValue()));
        Cell celdaValor = cellIterator.next();
        Double valor = celdaValor.getNumericCellValue();
        return new Consumo(tipo, valor);
    }

}

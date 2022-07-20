package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.MedioTransporte;
import domain.mediciones.consumos.Periodicidad;
import domain.mediciones.consumos.actividades.Logistica;
import domain.mediciones.consumos.tipoConsumo.ProductoTransportado;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.util.Iterator;

public class ImportarLogistica {

    @Setter
    private ImportarPeriodicidad importarPeriodicidad;

    public ImportarLogistica(ImportarPeriodicidad importarPeriodicidad){
        setImportarPeriodicidad(importarPeriodicidad);
    }

    private ProductoTransportado importarCategoria(Cell celda){
        switch (celda.getStringCellValue()){
            case "Materia prima":
                return ProductoTransportado.MATERIA_PRIMA;
            case "Insumos":
                return ProductoTransportado.INSUMOS;
            case "Productos vendidos":
                return ProductoTransportado.PRODUCTOS_VENDIDOS;
            case "Residuos":
                return ProductoTransportado.RESIDUOS;
            default:
                return null;
        }
    }

    private MedioTransporte importarMedioTransporte(Cell celda){
        switch (celda.getStringCellValue()){
            case "Camión de carga":
                return new MedioTransporte(8.0);
            case "Utilitario liviano":
                return new MedioTransporte(5.0);
            default:
                return null;
        }
    }

    public Logistica importar(Row filaActual, Iterator<Row> rowIterator){

        Iterator<Cell> iterador = filaActual.cellIterator();
        iterador.next(); // salteamos la celda de tipo de actividad
        iterador.next(); // salteamos la celda de nombre
        ProductoTransportado categoria = importarCategoria(iterador.next());
        Periodicidad periodicidad = importarPeriodicidad.importar(iterador);
        iterador = rowIterator.next().cellIterator();
        iterador.next(); // salteamos la celda de tipo de actividad
        iterador.next(); // salteamos la celda de nombre
        MedioTransporte medio = importarMedioTransporte(iterador.next());
        iterador = rowIterator.next().cellIterator();
        iterador.next(); // salteamos la celda de tipo de actividad
        iterador.next(); // salteamos la celda de nombre
        Cell distancia = iterador.next();
        iterador = rowIterator.next().cellIterator();
        iterador.next(); // salteamos la celda de tipo de actividad
        iterador.next(); // salteamos la celda de nombre
        Cell peso = iterador.next();
        return new Logistica(periodicidad, categoria, medio, distancia.getNumericCellValue(), peso.getNumericCellValue());
    }

}

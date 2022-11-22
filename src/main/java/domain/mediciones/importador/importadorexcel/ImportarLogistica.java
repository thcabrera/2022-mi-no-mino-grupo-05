package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.MedioTransporte;
import domain.mediciones.consumos.Periodicidad;
import domain.mediciones.consumos.actividades.Logistica;
import domain.mediciones.consumos.tipoConsumo.ProductoTransportado;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.xml.bind.ValidationException;
import java.util.*;

@Setter
public class ImportarLogistica {

    private Map<String, MedioTransporte> mediosTransporte;

    public ImportarLogistica(Map<String, MedioTransporte> mediosTransporte){
        setMediosTransporte(mediosTransporte);
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
        String valor = celda.getStringCellValue().toUpperCase();
        return this.mediosTransporte.get(valor);
    }

    @SneakyThrows
    public Logistica importar(Row filaActual, Iterator<Row> rowIterator){
        Integer anio = null;
        Integer mes = null;
        Iterator<Cell> iterador = filaActual.cellIterator();
        iterador.next(); // salteamos la celda de tipo de actividad
        iterador.next(); // salteamos la celda de nombre
        ProductoTransportado categoria = importarCategoria(iterador.next());
        switch(iterador.next().getStringCellValue()){
            case "Anual":
                anio = (int) iterador.next().getNumericCellValue();
                break;
            case "Mensual":
                Date date = iterador.next().getDateCellValue();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(date);
                anio = cal.get(Calendar.YEAR);
                mes = cal.get(Calendar.MONTH) + 1;
                break;
            default:
                throw new ValidationException("EL FORMATO ESPECIFICADO NO ES CORRECTO!");
        }
        iterador = rowIterator.next().cellIterator();
        iterador.next(); // salteamos la celda de tipo de actividad
//        iterador.next(); // salteamos la celda de nombre
        MedioTransporte medio = importarMedioTransporte(iterador.next());
        iterador = rowIterator.next().cellIterator();
        iterador.next(); // salteamos la celda de tipo de actividad
//        iterador.next(); // salteamos la celda de nombre
        Cell distancia = iterador.next();
        iterador = rowIterator.next().cellIterator();
        iterador.next(); // salteamos la celda de tipo de actividad
//        iterador.next(); // salteamos la celda de nombre
        Cell peso = iterador.next();
        return new Logistica(anio, mes, categoria, medio, distancia.getNumericCellValue(), peso.getNumericCellValue());
    }

}

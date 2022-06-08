package domain.mediciones.importador;
import domain.mediciones.consumos.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportarExcel implements AdapterImportadorExcel{
    private Iterator<Row> rowIterator;

    public ImportarExcel(String path){
        //agregar archivo al HSSFWorkbook()
        try{
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook archivoExcel = new XSSFWorkbook(file);
            XSSFSheet hoja = archivoExcel.getSheetAt(0);
            this.rowIterator = hoja.iterator();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void advanceIteratorTo(int i){
        for (int j = 0; j<i && this.rowIterator.hasNext(); j++) {
            rowIterator.next();
        }
    }

    public ArrayList<Actividad> importar(){
        ArrayList<Actividad> listadoActividades = new ArrayList<>();
        try {
            advanceIteratorTo(2);
            while (rowIterator.hasNext()) {
                Actividad actividad = this.siguiente();
                if (actividad == null)
                    break;
                listadoActividades.add(actividad);
            }
        } catch(Exception e) {
            System.out.println("Archivo de excel no compatible.");
            e.printStackTrace();
        }
        return listadoActividades;
    }

    private Consumo procesarConsumo(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next(); // Tipo de Consumo
        Consumo consumo = new Consumo();
        TipoConsumo tipoConsumo = obtenerTipoConsumo(cell.getStringCellValue());
        consumo.setTipoConsumo(tipoConsumo);

        cell = cellIterator.next(); // Consumo -> Valor
        consumo.setValor(((int) cell.getNumericCellValue()));

        cell = cellIterator.next(); // Consumo -> Periodicidad
        String tipoPeriodicidad = cell.getStringCellValue();
        cell = cellIterator.next(); // Periodo de imputacion
        String periodo = this.obtenerPeriodo(cell);
        Periodicidad periodicidad = obtenerTipoPeriodicidad(tipoPeriodicidad, periodo);
        consumo.setPeriodicidad(periodicidad);
        return consumo;
    }

    private Actividad siguiente(){
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();

        Actividad actividad;
        Cell celdaActividad = cellIterator.next();
        switch (celdaActividad.getStringCellValue()){ // Tipo Actividad
            case "LOGISTICA DE PRODUCTOS Y RESIDUOS":
                actividad = new Logistica();
                break;
            case "COMBUSTIÓN FIJA":
                actividad = new CombustionFija();
                break;
            case "COMBUSTIÓN MÓVIL":
                actividad = new CombustionMovil();
                break;
            case "ELECTRICIDAD": // TO revisar: realmente van a tener barras n ?
                actividad = new ElectricidadAdqYCons();
                break;
            default: //si no es ninguno de los otros casos, retornar null
                return null;
        }
        Consumo consumo = this.procesarConsumo(cellIterator);
        actividad.setConsumo(consumo);
        return actividad;
    }

    private TipoConsumo obtenerTipoConsumo(String tipo){
        switch(tipo) {
            case ("Gas Natural"):
                return new TipoConsumo("GAS NATURAL", Unidad.M3);
            case ("Diesel"):
            case ("Gasoil"):
                return new TipoConsumo("GASOIL", Unidad.LT);
            case ("Kerosene"):
                return new TipoConsumo("KEROSENE", Unidad.LT);
            case ("Fuel Oil"):
                return new TipoConsumo("FUEL OIL", Unidad.LT);
            case ("Nafta"):
                return new TipoConsumo("NAFTA", Unidad.LT);
            case ("Carbon"):
                return new TipoConsumo("CARBON", Unidad.KG);
            case ("Carbon de leña"):
                return new TipoConsumo("CARBON DE LEÑA", Unidad.KG);
            case ("Leña"):
                return new TipoConsumo("LEÑA", Unidad.KG);
            case ("GNC"):
                return new TipoConsumo("GNC", Unidad.LTS);
            case ("Electricidad"):
                return new TipoConsumo("ELECTRICIDAD", Unidad.KWH);
            case ("Peso total transportados"):
                return new TipoConsumo("PESO TOTAL TRANSP", Unidad.KG);
        }
    return null;
    }

    private String obtenerPeriodo(Cell cell){
        if (DateUtil.isCellDateFormatted(cell)){
            Date date = cell.getDateCellValue();
            DateFormat df = new SimpleDateFormat("MM/yyyy");
            return df.format(date);
        }
        else return String.valueOf((int) cell.getNumericCellValue());

    }

    private Periodicidad obtenerTipoPeriodicidad(String tipo, String periodo){
        switch (tipo) {
            case "Anual":
                return new Anual(periodo);
            case "Mensual":
                return new Mensual(periodo);
            default:
                return null;
        }
    }

}

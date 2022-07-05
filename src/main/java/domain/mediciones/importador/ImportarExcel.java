package domain.mediciones.importador;
import domain.mediciones.consumos.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import domain.mediciones.consumos.actividades.Actividad;
import domain.mediciones.consumos.tipoConsumo.*;
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
        consumo.setValor(( cell.getNumericCellValue())); // WARNING le saque el casteo a int porq no funcionaba  (int)

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
                return new GasNatural();
            case ("Diesel"):
            case ("Gasoil"):
                return new Gasoil();
            case ("Kerosene"):
                return new Kerosene();
            case ("Fuel Oil"):
                return new FuelOil();
            case ("Nafta"):
                return new Nafta();
            case ("Carbon"):
                return new Carbon();
            case ("Carbon de leña"):
                return new CarbonLeña();
            case ("Leña"):
                return new Leña();
            case ("GNC"):
                return new GNC();
            case ("Electricidad"):
                return new Electricidad();
            case ("Peso total transportados"):
                return new PesoTotal();
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

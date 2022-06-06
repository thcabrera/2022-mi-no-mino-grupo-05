package domain.mediciones.importador;
import domain.mediciones.consumos.*;

import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ImportarExcel implements AdapterImportadorExcel{

    private HSSFWorkbook archivoExcel;
    private HSSFSheet hoja;
    private Iterator<Row> rowIterator;

    public ImportarExcel(String path){
        //agregar archivo al HSSFWorkbook()
        this.archivoExcel = new HSSFWorkbook();
        this.hoja = archivoExcel.getSheetAt(0);
        this.rowIterator = hoja.iterator();
    }

    public ArrayList<Actividad> importar(String path){
        ArrayList<Actividad> listadoActividades = new ArrayList<Actividad>();
        try {
            //TODO hay que empezar desde la 3era
            if (rowIterator.hasNext() ){
                rowIterator.next();
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Actividad actividad = this.siguienteFila();
                listadoActividades.add(actividad);
                return listadoActividades;
            }
        } catch(Exception e) {
            System.out.println("Archivo de excel no compatible.");
            e.printStackTrace();
        }
        return listadoActividades;
    }
    private Actividad siguienteFila(){
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        Actividad actividad = null;
        Consumo consumo = null;

        Cell cell = cellIterator.next(); // Tipo de Actividad
        actividad = obtenerTipoActividad(cell.getStringCellValue());
        cell = cellIterator.next(); // Tipo de Consumo
        TipoConsumo tipoConsumo = obtenerTipoConsumo(cell.getStringCellValue());
        consumo.setTipoConsumo(tipoConsumo);

        cell = cellIterator.next(); // Consumo -> Valor
        consumo.setValor(Integer.valueOf(cell.getStringCellValue()));

        cell = cellIterator.next(); // Consumo -> Periodicidad
        String tipoPeriodicidad = cell.getStringCellValue();

        cell = cellIterator.next(); // Periodo de imputacion
        String periodo = cell.getStringCellValue();
        Periodicidad periodicidad = obtenerTipoPeriodicidad(tipoPeriodicidad, periodo);
        consumo.setPeriodicidad(periodicidad);

        actividad.setConsumo(consumo);
        return actividad;
    }

    public void cerrarImportador(){
        return;
    }

    public TipoConsumo obtenerTipoConsumo(String tipo){
        switch(tipo){
            // todo faltan definir los tipos de consumo en alguna parte del codigo que Fede Prandi nos diga
        }

        return null;
    }

    public Actividad obtenerTipoActividad(String tipo){

        Actividad actividad = null;

        switch (tipo){ // Tipo Actividad

            case "Combustion fija":
                actividad = new CombustionFija();
                break;
            case "Combustion movil":
                actividad = new CombustionMovil();
                break;
            case "Electricidad adquirida y consumida": // TO revisar: realmente van a tener barras n ?
                actividad = new ElectricidadAdqYCons();
                break;
            case "Logística de productos y\n" + "residuos":
                actividad = new Logistica();
                break;
        }

        return actividad;
    }

    public Periodicidad obtenerTipoPeriodicidad(String tipo, String periodo){
        try{
            switch (tipo) {
                case "Anual":
                    return new Anual(periodo);
                case "Mensual":
                    return new Mensual(periodo);
            }
        }catch (Exception e){
            // Todo:
            //  revisar, realmente se puede hacer el try catch? o con meterle un default en el switch
        }
        return null;
    }
}

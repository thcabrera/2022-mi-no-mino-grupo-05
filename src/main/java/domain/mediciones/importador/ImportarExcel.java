package domain.mediciones.importador;
import domain.mediciones.consumos.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportarExcel implements AdapterImportadorExcel{

    private XSSFWorkbook archivoExcel;
    private XSSFSheet hoja;
    private Iterator<Row> rowIterator;

    public ImportarExcel(String path){
        //agregar archivo al HSSFWorkbook()
        try{
            FileInputStream file = new FileInputStream(path);
            this.archivoExcel = new XSSFWorkbook(file);
            this.hoja = archivoExcel.getSheetAt(0);
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
        ArrayList<Actividad> listadoActividades = new ArrayList<Actividad>();
        try {
            //TODO hay que empezar desde la 3era (2da si contamos al 0)
            advanceIteratorTo(2);
            int cont = 0;
            while (rowIterator.hasNext()) {
                Actividad actividad = this.siguienteFila();
                listadoActividades.add(actividad);
                cont+=1;
                System.out.println(cont);
                if(cont == 12)
                    break;
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
        Consumo consumo = new Consumo();

        Cell cell = cellIterator.next(); // Tipo de Actividad
        actividad = obtenerActividad(cell.getStringCellValue());
        cell = cellIterator.next(); // Tipo de Consumo
        TipoConsumo tipoConsumo = obtenerTipoConsumo(cell.getStringCellValue());
        consumo.setTipoConsumo(tipoConsumo);

        cell = cellIterator.next(); // Consumo -> Valor
        consumo.setValor(((int) cell.getNumericCellValue()));

        cell = cellIterator.next(); // Consumo -> Periodicidad
        String tipoPeriodicidad = cell.getStringCellValue();
        cell = cellIterator.next(); // Periodo de imputacion
        String periodo = "";
        if (cell.getCellType() == CellType.STRING)
            periodo = cell.getStringCellValue();
        else periodo = String.valueOf(cell.getNumericCellValue());
        Periodicidad periodicidad = obtenerTipoPeriodicidad(tipoPeriodicidad, periodo);
        consumo.setPeriodicidad(periodicidad);
        actividad.setConsumo(consumo);
        return actividad;
    }

    public TipoConsumo obtenerTipoConsumo(String tipo){
        return new TipoConsumo();
    }

    public Actividad obtenerActividad(String tipo){

        Actividad actividad = null;

        switch (tipo){ // Tipo Actividad

            case "Combustion fija":
                actividad = new CombustionFija();
                break;
            case "Combustion móvil":
            case "Combustión móvil":
                actividad = new CombustionMovil();
                break;
            case "Electricidad": // TO revisar: realmente van a tener barras n ?
                actividad = new ElectricidadAdqYCons();
                break;
            case "Logistica de Productos y residuos":
                actividad = new Logistica();
                break;
            case "Productos y residuos":
                actividad = new ProductosYResiduos();
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

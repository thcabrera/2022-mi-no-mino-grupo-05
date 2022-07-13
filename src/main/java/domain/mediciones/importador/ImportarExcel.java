package domain.mediciones.importador;

import domain.mediciones.consumos.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import domain.mediciones.consumos.actividades.*;
import domain.mediciones.consumos.tipoConsumo.*;
import domain.mediciones.importador.importadorexcel.*;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportarExcel implements Importador{

    @Setter
    private ImportarLogistica importarLogistica;
    @Setter
    private ImportarActividadConsumo importarActividadConsumo;
    @Setter
    private ImportarConsumo importarConsumo;
    @Setter
    private ImportarTipoConsumo importarTipoConsumo;
    @Setter
    private ImportarPeriodicidad importarPeriodicidad;
    private Iterator<Row> rowIterator;

    public ImportarExcel(ImportarActividadConsumo importarActividadConsumo,
                         ImportarLogistica importarLogistica,
                         ImportarConsumo importarConsumo,
                         ImportarTipoConsumo importarTipoConsumo,
                         ImportarPeriodicidad importarPeriodicidad){
        setImportarActividadConsumo(importarActividadConsumo);
        setImportarLogistica(importarLogistica);
        setImportarConsumo(importarConsumo);
        setImportarTipoConsumo(importarTipoConsumo);
        setImportarPeriodicidad(importarPeriodicidad);
    }

    public ArrayList<Actividad> importar(String path){
        try{
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook archivoExcel = new XSSFWorkbook(file);
            XSSFSheet hoja = archivoExcel.getSheetAt(0);
            this.rowIterator = hoja.iterator();
            return importarActividades();
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void advanceIteratorTo(int i){
        for (int j = 0; j<i && this.rowIterator.hasNext(); j++) {
            rowIterator.next();
        }
    }

    private ArrayList<Actividad> importarActividades(){
        ArrayList<Actividad> listadoActividades = new ArrayList<>();
        try {
            advanceIteratorTo(2); // salteamos el encabezado
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

    private Actividad siguiente(){
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();

        Actividad actividad;
        Cell celdaActividad = cellIterator.next();
        if (celdaActividad.getStringCellValue().equals("LOGISTICA DE PRODUCTOS Y RESIDUOS")){
            return importarLogistica.importar(row, rowIterator);
        return importarActividadConsumo.importar(row.cellIterator());

    }

    //-------------- Logistica ------------------------
/*
    private ArrayList<Actividad>  simplificarLogistica(ArrayList<Actividad> actividades) { // TODO
        ArrayList<Actividad> logisticas =  actividades.stream().filter(act -> act.getConsumo().getValor() == 0.0);
        actividades.removeIf(act -> act.getClass().getSimpleName() == "Logistica"); // ademas de filtrarlas(no tiene side effect, habria q removerlas)
        Integer i = 0, j = 0;

        for(i = 0; i < logisticas.size(); i++) {
            for(j=0; j < 4; j++){
                Logistica aLogistica = procesarCampoLogistica(logisticas.get(i+j)); // EJ en i=0, j=[0-3] saco del 0-3, en i=1 j=[4-7] saco del 4-7
                actividades.add(aLogistica);
            }

        }
        return actividades;
    }
    private Logistica procesarCampoLogistica(Actividad actividad){
        Logistica aLogistica = new Logistica();

        switch (actividad.getConsumo().getTipoConsumo()){
            case ("categoria"):
                aLogistica.setCategoria(actividad.getConsumo().getValor()); // COSAS A CHARLAR CON THIAGUITOOOO
                break;
            case ("medioTransporte"):
                aLogistica.setMedioTransporte(actividad.getConsumo().getValor());
                break;
            case ("distancia"):
                aLogistica.setDistanciaMedia(actividad.getConsumo().getValor());
                break;
            case ("PESO"):
                aLogistica.setPeso(actividad.getConsumo().getValor());
                Consumo aConsumo = new Consumo();
                aConsumo.setPeriodicidad(actividad.getConsumo().getPeriodicidad());
                aConsumo.setValor(actividad.getConsumo().getValor());
                break;
        }
        // yo necesito que la categoria sea un enum de producto ej: MATERIA_PRIMA, deberia ser parte de la clase
        //Tipo Consumo o es mejor hacerlo una clase aparte, o hasta inclusive hacerla mas abstracta y de mayor jerarquia
    }
  */      /*
        ProductoTransportado categoria = leerCategoria();
        ProductoTransportado medioTransporte = leerCategoria();
        ProductoTransportado distanciaMedia = leerCategoria();
        ProductoTransportado peso = leerCategoria();

        //acordate que periodicidad esta dentro de la clase Consumo
        Periodicidad periodicidad = leerPeriodicidad();
        //Periodo imputacion???

        return new Logistica(categoria, medioTransporte, distanciaMedia, peso);
        */

    /*
    private ArrayList<Actividad> removeAndReturn(ArrayList<Actividad> actividades, Predicate comparator){
        ArrayList<Actividad> logisticas =  actividades.stream().filter(comparator);
        actividades.removeIf(comparator);
        return logisticas;
    }*/
}

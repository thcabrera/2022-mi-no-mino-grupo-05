package domain.mediciones.importador;
import domain.mediciones.consumos.*;

import java.beans.MethodDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import domain.mediciones.consumos.actividades.*;
import domain.mediciones.consumos.tipoConsumo.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportarExcel implements AdapterImportadorExcel{
    private Iterator<Row> rowIterator;
    private Carbon carbon;
    private CarbonLeña carbonLeña;
    private Electricidad electricidad;
    private FuelOil fuelOil;
    private GasNatural gasNatural;
    private Gasoil gasoil;
    private GNC gnc;
    private Kerosene kerosene;
    private Leña leña;
    private Nafta nafta;
    private MedioTransporte camionDeCarga;
    private MedioTransporte camionLivianoUtilitario;

    private void crearInstancias(){
        carbon = new Carbon();
        carbonLeña = new CarbonLeña();
        electricidad = new Electricidad();
        fuelOil = new FuelOil();
        gasNatural = new GasNatural();
        gasoil = new Gasoil();
        gnc = new GNC();
        kerosene  = new Kerosene();
        leña = new Leña();
        nafta = new Nafta();
        camionDeCarga = new MedioTransporte(2.0);
        camionLivianoUtilitario = new MedioTransporte(1.2);
    }

    public ImportarExcel(String path){
        crearInstancias(); // creamos todas las instancias que van a ser unicas
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
            case "COMBUSTIÓN FIJA":
                actividad = new CombustionFija();
                break;
            case "COMBUSTIÓN MÓVIL":
                actividad = new CombustionMovil();
                break;
            case "ELECTRICIDAD": // TO revisar: realmente van a tener barras n ?
                actividad = new ElectricidadAdqYCons();
                break;
            case "LOGISTICA DE PRODUCTOS Y RESIDUOS":
                actividad = new Logistica();
//                actividad = this.procesarLogistica(actividad, cellIterator);
//                return actividad;
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
                return gasNatural;
            case ("Diesel"):
            case ("Gasoil"):
                return gasoil;
            case ("Kerosene"):
                return kerosene;
            case ("Fuel Oil"):
                return fuelOil;
            case ("Nafta"):
                return nafta;
            case ("Carbon"):
                return carbon;
            case ("Carbon de leña"):
                return carbonLeña; //Cambiar a CarbonLenia
            case ("Leña"):
                return leña; //Cambiar a Lenia
            case ("GNC"):
                return gnc;
            case ("Electricidad"):
                return electricidad;
            case ("Peso total transportados"):
                return new PesoTotal(); //TODO
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
                return new Anual(obtenerAnioPeriodoAnual(periodo));
            case "Mensual":
                return new Mensual(obtenerMesPeriodoMensual(periodo), obtenerAnioPeriodoMensual(periodo));
            default:
                return null;
        }
    }

    private Integer obtenerAnioPeriodoAnual(String periodo){
        return Integer.valueOf(periodo);
    }

    private Integer obtenerMesPeriodoMensual(String periodo){
        String[] parts = periodo.split("/");
        String mes = parts[0];
        return Integer.valueOf(mes);
    }

    private Integer obtenerAnioPeriodoMensual(String periodo){
        String[] parts = periodo.split("/");
        String anio = parts[1];
        return obtenerAnioPeriodoAnual(anio);
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

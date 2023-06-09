package domain.mediciones.importador;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import domain.mediciones.consumos.MedioTransporte;
import domain.mediciones.consumos.actividades.*;
import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.importador.importadorexcel.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Getter
@Setter
public class ImportarExcel implements Importador{

    private ImportarLogistica importarLogistica;
    private ImportarActividadConsumo importarActividadConsumo;
    private ImportarConsumo importarConsumo;
    private ImportarTipoConsumo importarTipoConsumoFijo;
    private ImportarTipoConsumo importarTipoConsumoMovil;
    private ImportarTipoConsumo importarTipoConsumoElectricidad;
    private Iterator<Row> rowIterator;

    public ImportarExcel(Map<String, TipoConsumo> consumosFijos,
                         Map<String, TipoConsumo> consumosMoviles,
                         Map<String, TipoConsumo> consumosElectricidad,
                         Map<String, MedioTransporte> mediosTransporte){
        importarTipoConsumoFijo = new ImportarTipoConsumo(consumosFijos);
        importarTipoConsumoMovil = new ImportarTipoConsumo(consumosMoviles);
        importarTipoConsumoElectricidad = new ImportarTipoConsumo(consumosElectricidad);
        importarConsumo = new ImportarConsumo();
        importarActividadConsumo = new ImportarActividadConsumo(importarConsumo,
                importarTipoConsumoFijo, importarTipoConsumoMovil, importarTipoConsumoElectricidad);
        importarLogistica = new ImportarLogistica(mediosTransporte);
    }

    public List<Actividad> importar(String path){
        List<Actividad> actividades = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(path); XSSFWorkbook archivoExcel = new XSSFWorkbook(file)) {
            XSSFSheet hoja = archivoExcel.getSheetAt(0);
            this.rowIterator = hoja.iterator();
            actividades = importarActividades();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return actividades;
    }

    private void advanceIteratorTo(int i){
        for (int j = 0; j<i && this.rowIterator.hasNext(); j++) {
            rowIterator.next();
        }
    }

    private List<Actividad> importarActividades(){
        List<Actividad> listadoActividades = new ArrayList<>();
        try {
            advanceIteratorTo(2); // salteamos el encabezado
            while (rowIterator.hasNext()) {
                Actividad actividad = this.siguiente();
                if (actividad == null)
                    break;
                listadoActividades.add(actividad);
            }
        } catch(Exception e) {
            System.out.println("Archivo de excel no compatible.\n" + e.getMessage());
            return null;
        }

        return listadoActividades;
    }

    private Actividad siguiente(){
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();

        if (cellIterator.next().getStringCellValue().equals("LOGISTICA DE PRODUCTOS Y RESIDUOS"))
            return importarLogistica.importar(row, rowIterator);
        return importarActividadConsumo.importar(row);
    }

}

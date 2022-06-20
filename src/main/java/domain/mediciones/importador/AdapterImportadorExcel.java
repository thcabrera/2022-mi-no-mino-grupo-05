package domain.mediciones.importador;
import domain.mediciones.consumos.actividades.Actividad;
import java.util.ArrayList;

public interface AdapterImportadorExcel {
    ArrayList<Actividad> importar();
}

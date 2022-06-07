package domain.mediciones.importador;
import domain.mediciones.consumos.Actividad;
import java.util.ArrayList;

public interface AdapterImportadorExcel {
    ArrayList<Actividad> importar();
}

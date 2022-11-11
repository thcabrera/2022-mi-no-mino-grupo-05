package domain.mediciones.importador;

import domain.mediciones.consumos.actividades.Actividad;
import java.util.List;

public interface Importador {

    List<Actividad> importar(String path);

}

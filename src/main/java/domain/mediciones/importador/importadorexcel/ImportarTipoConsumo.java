package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.tipoConsumo.*;

public interface ImportarTipoConsumo {

    TipoConsumo importar(String tipo);

}

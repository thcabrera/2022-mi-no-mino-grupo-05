package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import lombok.Setter;
import java.util.Map;

public class ImportarTipoConsumo{
    @Setter
    private Map<String, TipoConsumo> tipos;

    public ImportarTipoConsumo(Map<String, TipoConsumo> tipos){
        setTipos(tipos);
    }

    public TipoConsumo importar(String tipo){
        tipo = tipo.toUpperCase();
        return this.tipos.get(tipo);
    }


}

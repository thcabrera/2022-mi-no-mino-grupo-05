package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.consumos.tipoConsumo.Unidad;

public class ImportarTipoConsumoElectricidad implements ImportarTipoConsumo{

    private TipoConsumo electricidad;

    public ImportarTipoConsumoElectricidad(){
        this.electricidad = new TipoConsumo(Unidad.KWH, 1.0);
    }

    public TipoConsumo importar(String tipo){
        // podría ser un if perfectamente, pero dejo el switch para que sea extensible
        switch(tipo){
            case "ELECTRICIDAD":
                return electricidad;
            default:
                return null;
        }
    }

}

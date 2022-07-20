package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.consumos.tipoConsumo.Unidad;

public class ImportarTipoConsumoMovil implements ImportarTipoConsumo{

    private TipoConsumo gasoil;
    private TipoConsumo gnc;
    private TipoConsumo nafta;

    public ImportarTipoConsumoMovil(){
        gasoil = new TipoConsumo(Unidad.LTS, 1.8);
        gnc = new TipoConsumo(Unidad.LTS, 2.8);
        nafta = new TipoConsumo(Unidad.LTS, 2.8);
    }

    public TipoConsumo importar(String tipo){
        switch(tipo) {
            case ("Gasoil"):
                return gasoil;
            case ("Nafta"):
                return nafta;
            case ("GNC"):
                return gnc;
        }
        return null;

    }


}

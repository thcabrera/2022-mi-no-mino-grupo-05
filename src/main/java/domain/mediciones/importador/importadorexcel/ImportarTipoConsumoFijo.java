package domain.mediciones.importador.importadorexcel;

import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.consumos.tipoConsumo.Unidad;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Setter;

import java.util.Map;


public class ImportarTipoConsumoFijo implements ImportarTipoConsumo{
    @Setter
    private Map<String, TipoConsumo> map;

    public ImportarTipoConsumoFijo() {
        //todo: sacarlos del map recien creado por thiaguin
        /*
        carbon = new TipoConsumo(Unidad.KG, 1.5);
        carbonLeña = new TipoConsumo(Unidad.KG, 1.5);
        fuelOil = new TipoConsumo(Unidad.LT, 3.0);
        gasNatural = new TipoConsumo(Unidad.M3, 1.1);
        gasoil = new TipoConsumo(Unidad.LT, 1.8);
        kerosene  = new TipoConsumo(Unidad.LT, 3.0);
        leña = new TipoConsumo(Unidad.KG, 1.5);
        nafta = new TipoConsumo(Unidad.LT, 2.8);

         */

    }
    public TipoConsumo importar(String tipo){
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
            default:
                return null;
        }

    }


}

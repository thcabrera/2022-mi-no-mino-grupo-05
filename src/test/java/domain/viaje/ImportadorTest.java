package domain.viaje;
import domain.mediciones.consumos.*;
import domain.mediciones.consumos.actividades.Actividad;
import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.consumos.tipoConsumo.Unidad;
import domain.mediciones.importador.ImportarExcel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

public class ImportadorTest {

    String EXCEL_PATH = "src/test/java/resources/Excel para modulo importador.xlsx";

    private ArrayList<Actividad> actividades;
    private Actividad actividadFalsa;

    @BeforeEach
 /*   void setupThis()  {
        ImportarExcel importador = new ImportarExcel(EXCEL_PATH);
        this.actividades = importador.importar();

        this.actividadFalsa = new CombustionFija();
        Consumo c = new Consumo();
        c.setTipoConsumo(new TipoConsumo("GAS NATURAL", Unidad.M3));
        c.setPeriodicidad(new Anual("2002"));
        c.setValor(1000);
        this.actividadFalsa.setConsumo(c);
    }

    @Test
    public void mappeaBienTipoConsumo() {
        Actividad actividad = actividades.get(0);
        Assertions.assertEquals(actividad.getConsumo().getTipoConsumo().getClass(),
                this.actividadFalsa.getConsumo().getTipoConsumo().getClass());
        Assertions.assertEquals(actividad.getConsumo().getTipoConsumo().getNombre(),
                this.actividadFalsa.getConsumo().getTipoConsumo().getNombre());
        Assertions.assertEquals(actividad.getConsumo().getTipoConsumo().getUnidad(),
                this.actividadFalsa.getConsumo().getTipoConsumo().getUnidad());
    }
*/
    @Test
    public void mappeaBienValor(){
        Actividad actividad = actividades.get(0);
        Assertions.assertEquals(actividad.getConsumo().getValor(),
                this.actividadFalsa.getConsumo().getValor());
    }

    @Test
    public void mappeaBienPeriodicidad(){
        Actividad actividad = actividades.get(0);
        Assertions.assertEquals(this.actividadFalsa.getConsumo().getPeriodicidad().getClass(),
                actividad.getConsumo().getPeriodicidad().getClass());
        Assertions.assertEquals(this.actividadFalsa.getConsumo().getPeriodicidad().getPeriodo(),
                actividad.getConsumo().getPeriodicidad().getPeriodo());
    }

    @Test
    public void mostrarActividadesLeidas(){
        for(Actividad actividad: this.actividades)
            System.out.println(actividad+"\n-------------\n");
    }


}

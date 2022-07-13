package domain.viaje;
import domain.mediciones.consumos.*;
import domain.mediciones.consumos.actividades.Actividad;
import domain.mediciones.consumos.actividades.ActividadConsumo;
import domain.mediciones.consumos.actividades.Logistica;
import domain.mediciones.consumos.actividades.TipoActividadConsumo;
import domain.mediciones.consumos.tipoConsumo.ProductoTransportado;
import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.consumos.tipoConsumo.Unidad;
import domain.mediciones.importador.ImportarExcel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ImportadorTest {

    String EXCEL_PATH = "src/test/java/resources/Excel para modulo importador.xlsx";

    private ActividadConsumo actividadConsumoFalsa;
    private ActividadConsumo actividadConsumo;

    private Logistica actividadLogisticaFalsa;
    private Logistica actividadLogistica;

    @BeforeAll
    void setup(){
        ImportarExcel importador = new ImportarExcel();
        ArrayList<Actividad> actividades = importador.importar(EXCEL_PATH);
        this.actividadConsumoFalsa = new ActividadConsumo(new Anual(2002),
                new Consumo(new TipoConsumo(Unidad.M3, 2.0), 1000.0),
                TipoActividadConsumo.COMBUSTION_FIJA);
        this.actividadLogisticaFalsa = new Logistica(new Anual(1977),
                ProductoTransportado.MATERIA_PRIMA, new MedioTransporte(8.0), 100.0, 4500.0);
        this.actividadConsumo = (ActividadConsumo) actividades.get(0);
        this.actividadLogistica = (Logistica) actividades.get(10);
    }

    @Test
    public void mappeaBienTipoConsumo() {
        Assertions.assertEquals(this.actividadConsumoFalsa.getConsumo().getTipoConsumo(),
                this.actividadConsumo.getConsumo().getTipoConsumo());
    }

    @Test
    public void mappeaBienValor(){
        Assertions.assertEquals(this.actividadConsumoFalsa.getConsumo().getValor(),
                this.actividadConsumo.getConsumo().getValor());
    }

    @Test
    public void mappeaBienPeriodicidadParaConsumo(){
        Assertions.assertEquals(this.actividadConsumoFalsa.getPeriodicidad(),
                this.actividadConsumo.getPeriodicidad());
    }

    @Test
    public void mappeaBienPeriodicidadParaLogistica(){
        Assertions.assertEquals(this.actividadLogisticaFalsa.getPeriodicidad(),
                this.actividadLogistica.getPeriodicidad());
    }

    @Test
    public void mappeaBienMedioDeTransporte(){
        Assertions.assertEquals(this.actividadLogisticaFalsa.getMedioTransporte(),
                this.actividadLogistica.getMedioTransporte());
    }

    @Test
    public void mappeaBienCategoria(){
        Assertions.assertEquals(this.actividadLogisticaFalsa.getCategoria(),
                this.actividadLogistica.getCategoria());
    }

    @Test
    public void mappeaBienDistanciaMedia(){
        Assertions.assertEquals(this.actividadLogisticaFalsa.getDistanciaMedia(),
                this.actividadLogistica.getDistanciaMedia());
    }

    @Test
    public void mappeaBienPesoTotal(){
        Assertions.assertEquals(this.actividadLogisticaFalsa.getPeso(),
                this.actividadLogistica.getPeso());
    }

}

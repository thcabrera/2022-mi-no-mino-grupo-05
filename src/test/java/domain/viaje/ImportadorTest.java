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
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ImportadorTest {

    private ActividadConsumo actividadConsumoGasNatural, actividadConsumoGasNaturalImportada,
            actividadConsumoElectricidad, actividadConsumoElectricidadImportada,
            actividadConsumoGNC, actividadConsumoGNCImportada;

    private Logistica actividadLogisticaCamion, actividadLogisticaCamionImportada,
            actividadLogisticaUtilitario, actividadLogisticaUtilitarioImportada;

    private List<Actividad> actividadesImportadas;

    private Map<String, TipoConsumo> obtenerTiposConsumoFijos() {
        TipoConsumo gasNatural = new TipoConsumo(null, Unidad.LT, 1.5, "GAS NATURAL", TipoActividadConsumo.COMBUSTION_FIJA);
        TipoConsumo kerosene = new TipoConsumo(null, Unidad.LT, 1.5, "KEROSENE", TipoActividadConsumo.COMBUSTION_FIJA);
        TipoConsumo fuelOil = new TipoConsumo(null, Unidad.LT, 1.5, "FUEL OIL", TipoActividadConsumo.COMBUSTION_FIJA);
        TipoConsumo nafta = new TipoConsumo(null, Unidad.LT, 1.5, "NAFTA", TipoActividadConsumo.COMBUSTION_FIJA);
        TipoConsumo carbon = new TipoConsumo(null, Unidad.LT, 1.5, "CARBÓN", TipoActividadConsumo.COMBUSTION_FIJA);
        TipoConsumo carbonDeLenia = new TipoConsumo(null, Unidad.LT, 1.5, "CARBÓN DE LEÑA", TipoActividadConsumo.COMBUSTION_FIJA);
        TipoConsumo lenia = new TipoConsumo(null, Unidad.LT, 1.5, "LEÑA", TipoActividadConsumo.COMBUSTION_FIJA);
        List<TipoConsumo> tipoConsumoList = new ArrayList<>(Arrays.asList(gasNatural, kerosene, fuelOil, nafta, carbon, carbonDeLenia,lenia));
        return tipoConsumoList.stream().collect(Collectors.toMap(TipoConsumo::getDescripcion, item -> item));
    }

    private Map<String, TipoConsumo> obtenerTiposConsumoMoviles() {
        TipoConsumo gnc = new TipoConsumo(null, Unidad.LT, 1.5, "GNC", TipoActividadConsumo.COMBUSTION_MOVIL);
        TipoConsumo gasoil = new TipoConsumo(null, Unidad.LT, 1.5, "GASOIL", TipoActividadConsumo.COMBUSTION_MOVIL);
        List<TipoConsumo> tipoConsumoList = new ArrayList<>(Arrays.asList(gnc, gasoil));
        return tipoConsumoList.stream().collect(Collectors.toMap(TipoConsumo::getDescripcion, item -> item));
    }

    private Map<String, TipoConsumo> obtenerTiposConsumoElectricidad() {
        TipoConsumo electricidad = new TipoConsumo(null, Unidad.LT, 1.5, "ELECTRICIDAD", TipoActividadConsumo.ELECTRICIDAD);
        List<TipoConsumo> tipoConsumoList = new ArrayList<>(Collections.singletonList(electricidad));
        return tipoConsumoList.stream().collect(Collectors.toMap(TipoConsumo::getDescripcion, item -> item));
    }

    private Map<String, MedioTransporte> obtenerMediosDeTransporte() {
        MedioTransporte camionDeCarga = new MedioTransporte(null, "CAMIÓN DE CARGA", 20.0);
        MedioTransporte utilitarioLiviano = new MedioTransporte(null, "UTILITARIO LIVIANO", 5.0);
        List<MedioTransporte> medioTransporteList = new ArrayList<>(Arrays.asList(camionDeCarga, utilitarioLiviano));
        return medioTransporteList.stream().collect(Collectors.toMap(MedioTransporte::getDescripcion, item -> item));
    }

    @BeforeEach
    void setup(){
        Map<String, TipoConsumo> tiposConsumoFijos = obtenerTiposConsumoFijos();
        Map<String, TipoConsumo> tiposConsumoMoviles = obtenerTiposConsumoMoviles();
        Map<String, TipoConsumo> tiposConsumoElectricidad = obtenerTiposConsumoElectricidad();
        Map<String, MedioTransporte> mediosDeTransporte = obtenerMediosDeTransporte();

        ImportarExcel importador = new ImportarExcel(
                tiposConsumoFijos,
                tiposConsumoMoviles,
                tiposConsumoElectricidad,
                mediosDeTransporte);
        String EXCEL_PATH = "src/test/java/resources/Excel para modulo importador.xlsx";
        actividadesImportadas = importador.importar(EXCEL_PATH);
        actividadConsumoGasNatural = new ActividadConsumo(
                2002,
                null,
                new Consumo(tiposConsumoFijos.get("GAS NATURAL"), 1000.0),
                TipoActividadConsumo.COMBUSTION_FIJA);
        actividadConsumoGNC = new ActividadConsumo(
                2020,
                5,
                new Consumo(tiposConsumoMoviles.get("GNC"), 15.0),
                TipoActividadConsumo.COMBUSTION_MOVIL
        );
        actividadConsumoElectricidad = new ActividadConsumo(
                2021,
                11,
                new Consumo(tiposConsumoElectricidad.get("ELECTRICIDAD"), 10.0),
                TipoActividadConsumo.ELECTRICIDAD
        );
        actividadLogisticaCamion = new Logistica(
                1977,
                null,
                ProductoTransportado.MATERIA_PRIMA,
                mediosDeTransporte.get("CAMIÓN DE CARGA"),
                100.0,
                4500.0);
        actividadLogisticaUtilitario = new Logistica(
                1977,
                null,
                ProductoTransportado.MATERIA_PRIMA,
                mediosDeTransporte.get("UTILITARIO LIVIANO"),
                100.0,
                4500.0);
        actividadConsumoGasNaturalImportada = (ActividadConsumo) actividadesImportadas.get(0);
        actividadConsumoGNCImportada = (ActividadConsumo) actividadesImportadas.get(10);
        actividadConsumoElectricidadImportada = (ActividadConsumo) actividadesImportadas.get(11);
        actividadLogisticaCamionImportada = (Logistica) actividadesImportadas.get(5);
        actividadLogisticaUtilitarioImportada = (Logistica) actividadesImportadas.get(6);
    }

    private void testearTipoConsumo(ActividadConsumo actividadReal, ActividadConsumo actividadEsperada) {
        Assertions.assertEquals(
                actividadEsperada.getConsumo().getTipoConsumo(),
                actividadReal.getConsumo().getTipoConsumo(),
                "No mapea bien el tipo de consumo");
    }

    private void testearValorDelConsumo(ActividadConsumo actividadReal, ActividadConsumo actividadEsperada) {
        Assertions.assertEquals(
                actividadEsperada.getConsumo().getValor(),
                actividadReal.getConsumo().getValor(),
                "No mapea bien el valor del consumo");
    }


    @Test
    public void mappeaBienConsumos() {
        testearTipoConsumo(actividadConsumoGasNatural, actividadConsumoGasNaturalImportada);
        testearTipoConsumo(actividadConsumoGNC, actividadConsumoGNCImportada);
        testearTipoConsumo(actividadConsumoElectricidad, actividadConsumoElectricidadImportada);
        testearValorDelConsumo(actividadConsumoGasNatural, actividadConsumoGasNaturalImportada);
        testearValorDelConsumo(actividadConsumoGNC, actividadConsumoGNCImportada);
        testearValorDelConsumo(actividadConsumoElectricidad, actividadConsumoElectricidadImportada);
    }

    private void testearPeriodicidad(Actividad actividadReal, Actividad actividadEsperada) {
        Assertions.assertEquals(
                actividadEsperada.getAnio(),
                actividadReal.getAnio(),
                "Falla el mappeo del año de la periodicidad"
        );
        if (Periodicidad.esMensual(actividadEsperada.getAnio(), actividadEsperada.getMes())){
            Assertions.assertEquals(
                    actividadEsperada.getMes(),
                    actividadReal.getMes(),
                    "Falla el mappeo del mes de la periodicidad"
            );

        }
    }

    @Test
    public void mappeaBienPeriodicidad(){
        testearPeriodicidad(actividadConsumoGasNatural, actividadConsumoGasNaturalImportada);
        testearPeriodicidad(actividadConsumoGNC, actividadConsumoGNCImportada);
        testearPeriodicidad(actividadConsumoElectricidad, actividadConsumoElectricidadImportada);
        testearPeriodicidad(actividadLogisticaCamion, actividadLogisticaCamionImportada);
        testearPeriodicidad(actividadLogisticaUtilitario, actividadLogisticaUtilitarioImportada);
    }

    private void testearMedioDeTransporte(Logistica logisticaReal, Logistica logisticaEsperada) {
        Assertions.assertEquals(
                logisticaEsperada.getMedioTransporte(),
                logisticaReal.getMedioTransporte(),
                "Falla el mappeo del medio de transporte en logística");
    }

    private void testearCategoriaDeProductos(Logistica logisticaReal, Logistica logisticaEsperada) {
        Assertions.assertEquals(
                logisticaEsperada.getCategoria(),
                logisticaReal.getCategoria(),
                "Falla el mappeo de la categoría de productos en logística");
    }

    private void testearPesoDeLogistica(Logistica logisticaReal, Logistica logisticaEsperada) {
        Assertions.assertEquals(
                logisticaEsperada.getPeso(),
                logisticaReal.getPeso(),
                "Falla el mappeo del peso en logística");
    }

    private void testearDistanciaMediaDeLogistica(Logistica logisticaReal, Logistica logisticaEsperada) {
        Assertions.assertEquals(
                logisticaEsperada.getDistanciaMedia(),
                logisticaReal.getDistanciaMedia(),
                "Falla el mappeo de la distancia media en logística");
    }


    @Test
    public void mappeaBienValoresDeLogistica(){
        // PESO
        testearPesoDeLogistica(actividadLogisticaCamion, actividadLogisticaCamionImportada);
        testearPesoDeLogistica(actividadLogisticaUtilitario, actividadLogisticaUtilitarioImportada);
        // DISTANCIA MEDIA
        testearDistanciaMediaDeLogistica(actividadLogisticaCamion, actividadLogisticaCamionImportada);
        testearDistanciaMediaDeLogistica(actividadLogisticaUtilitario, actividadLogisticaUtilitarioImportada);
        // CATEGORIA DE PRODUCTOS
        testearCategoriaDeProductos(actividadLogisticaCamion, actividadLogisticaCamionImportada);
        testearCategoriaDeProductos(actividadLogisticaUtilitario, actividadLogisticaUtilitarioImportada);
        // MEDIO DE TRANSPORTE
        testearMedioDeTransporte(actividadLogisticaCamion, actividadLogisticaCamionImportada);
        testearMedioDeTransporte(actividadLogisticaUtilitario, actividadLogisticaUtilitarioImportada);
    }

    @Test
    public void mappeaTodasLasActividadesDelExcel(){
        // son 12 actividades
        Assertions.assertEquals(
                12,
                actividadesImportadas.size(),
                "No mappea todas las actividades");
    }

}

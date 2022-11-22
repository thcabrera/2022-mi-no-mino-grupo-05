package domain.calculoMediciones;

import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;
import domain.mediciones.consumos.actividades.Actividad;
import domain.mediciones.consumos.actividades.ActividadConsumo;
import domain.mediciones.consumos.actividades.TipoActividadConsumo;
import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.consumos.tipoConsumo.Unidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositorios.RepositorioActividades;
import java.util.List;

public class CalculoPeriodicidadesTest {

    @Test
    public void calculoPeriodicidad(){
        Integer anio = 2022;
        Integer mes = 11;
        TipoConsumo tipoConsumo = new TipoConsumo(Unidad.LT, 20.5);
        tipoConsumo.setTipoActividad(TipoActividadConsumo.COMBUSTION_MOVIL);
        tipoConsumo.setDescripcion("COCO");
        Consumo consumo = new Consumo(tipoConsumo, 500.0);
        Actividad actividad = new ActividadConsumo(2022, 11, consumo, TipoActividadConsumo.COMBUSTION_MOVIL);
        Assertions.assertEquals(1/12, Periodicidad.obtenerPorcentaje(anio, mes, 2022, 10));
        Assertions.assertTrue(actividad.creadaEntre(2022, 11));
        Assertions.assertTrue(actividad.creadaEntre(2022, null));
        System.out.println(new RepositorioActividades().queryMensual(2022, 11));
    }

    @Test
    public void selectDeActividadesFunciona(){
        Integer anio = 1977;
        Integer mes = null;

        RepositorioActividades repositorioActividades = new RepositorioActividades();
        List<Actividad> actividades = repositorioActividades.buscarActividadesPorPeriodo(anio, mes);
        Assertions.assertEquals(
                4500000.0,
                actividades.stream().mapToDouble(a -> a.calculoHC(anio, mes)).sum(),
                "Fallo el calculo de HC ANUAL de las actividades. Por favor, reintentelo " +
                        "nuevamente borrando las actividades e importando el excel que se encuentra " +
                        "en test/resources");
        Assertions.assertEquals(
                375000.0,
                actividades.stream().mapToDouble(a -> a.calculoHC(anio, 4)).sum(),
                "Fallo el calculo de HC MENSUAL de las actividades. Por favor, reintentelo " +
                        "nuevamente borrando las actividades e importando el excel que se encuentra " +
                        "en test/resources");
    }


}

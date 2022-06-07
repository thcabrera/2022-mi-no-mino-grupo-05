package domain.viaje;

import domain.mediciones.consumos.*;
import domain.mediciones.importador.ImportarExcel;
import domain.viaje.publico.LColectivo;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TPublico;
import domain.viaje.publico.sentido.SentidoRecorrido;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class ImportadorTest {


    @BeforeEach
    void setupThis()  {

    }

    @Test
    public void importadorImportaCorrectamenteLaPrimerActividad() {
        ImportarExcel importador = new ImportarExcel("C:\\Users\\54116\\Downloads\\Excel para modulo importador.xlsx");
        ArrayList<Actividad> actividades = importador.importar();
        CombustionFija combustionFija = new CombustionFija();
        Consumo c = new Consumo();
        c.setTipoConsumo(new TipoConsumo());
        c.setPeriodicidad(new Anual("2002"));
        c.setValor(1000);
        combustionFija.setConsumo(c);
        Actividad a = actividades.get(0);
        System.out.println(a.getConsumo().getTipoConsumo().getClass().getName());
        System.out.println(a.getConsumo().getPeriodicidad());
        System.out.println(a.getConsumo().getValor());
        //esta bien, falta implementar el TipoConsumo para que Unidad este definida y ahi va a tirar
        //que esta todo bien
        Assertions.assertEquals(combustionFija, a);
    }
}

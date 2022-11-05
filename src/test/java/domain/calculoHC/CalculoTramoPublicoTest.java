package domain.calculoHC;

import domain.entidades.Persona;
import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TipoLinea;
import domain.viaje.publico.TramoPublico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class CalculoTramoPublicoTest {

    private TramoPublico tramoPublicoEnIda;
    private TramoPublico tramoPublicoEnVuelta;

    @BeforeEach
    public void setUpThis(){
        // ------------ LINEA -----------------
        Linea linea = new Linea();
        TipoLinea tipoLinea = new TipoLinea();
        tipoLinea.setConsumo(40.0);
        linea.setTipoLinea(tipoLinea);

        // -------------- PARADAS -------------
        Parada parada1 = new Parada();
        parada1.setId(0);
        Parada parada2 = new Parada();
        parada2.setIndice(1);
        parada1.setDistanciaSigParada(100.0);
        parada2.setDistanciaAntParada(100.0);
        Parada parada3 = new Parada();
        parada3.setIndice(3);
        parada2.setDistanciaSigParada(90.0);
        parada3.setDistanciaAntParada(90.0);
        linea.setParadas(new ArrayList<>(Arrays.asList(parada1, parada2, parada3)));
        // ------------------ TRAMO -----------------
        tramoPublicoEnIda = new TramoPublico(parada1, parada3, linea);
        tramoPublicoEnVuelta = new TramoPublico(parada3, parada1, linea);
    }

    @Test
    public void tramoPublicoCalculaBienSuDistanciaEnIda(){
        Assertions.assertEquals(190.0, tramoPublicoEnIda.getDistancia(),
                "El tramo público en ida no calcula bien su distancia");
    }

    @Test
    public void tramoPublicoCalculaBienSuHCEnIda(){
        Assertions.assertEquals(190.0*40.0, tramoPublicoEnIda.calculoHC(new Persona()),
                "El tramo público en ida no calcula bien su HC");
    }

    @Test
    public void tramoPublicoCalculaBienSuDistanciaEnVuelta(){
        Assertions.assertEquals(190.0, tramoPublicoEnVuelta.getDistancia(),
                "El tramo público en vuelta no calcula bien su distancia");
    }

    @Test
    public void tramoPublicoCalculaBienSuHCEnVuelta(){
        Assertions.assertEquals(190.0*40.0, tramoPublicoEnVuelta.calculoHC(new Persona()),
                "El tramo público en vuelta no calcula bien su HC");
    }


}

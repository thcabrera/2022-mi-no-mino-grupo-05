package domain.viaje;

import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TPublico;
import domain.viaje.publico.sentido.SentidoRecorrido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class PublicoTest {
    private Parada cortina, marmokix, corrientes, mozart, xokas, lenny_y_Alberdi;
    private Linea tresSiete;
    private List<Parada> paradasIntermedias;
    private Parada unaParada;
    private Parada otraParada;

    private TPublico tramoPublicoDeIda;
    private TPublico tramoPublicoDeVuelta;

    @BeforeEach
    void setupThis()  {
       // tresSiete = new Linea(""); // creo q no sirve para nada el consumoPorKM
        unaParada = new Parada("UnaParada");
        otraParada = new Parada("OtraParada");
        cortina = new Parada("Cortina");
        marmokix = new Parada("Marmokix");
        corrientes = new Parada("Corrientes");
        mozart = new Parada("Mozart");
        xokas = new Parada("Xokas");
        lenny_y_Alberdi = new Parada("Lenny");
        cortina.setDistanciaSigParada(100.0);
        marmokix.setDistanciaSigParada(100.0);
        corrientes.setDistanciaSigParada(100.0);
        mozart.setDistanciaSigParada(100.0);
        marmokix.setDistanciaAntParada(100.0);
        corrientes.setDistanciaAntParada(100.0);
        mozart.setDistanciaAntParada(100.0);
        tramoPublicoDeIda = new TPublico(cortina, xokas, tresSiete);
        tramoPublicoDeVuelta = new TPublico(mozart, cortina, tresSiete);

        paradasIntermedias = new ArrayList<>();
    }

    @Test
    public void unColectivoAgregaParadas() {

        tresSiete.agregarParadas(unaParada);
        tresSiete.agregarParadas(otraParada);
        Assertions.assertNotNull(unaParada);
        Assertions.assertEquals(unaParada, tresSiete.getParadas().get(0));
    }

    @Test
    public void lineaTiene3ParadasIntermediasDeIda(){

        tresSiete.agregarParadas(cortina, marmokix, corrientes, mozart, xokas, lenny_y_Alberdi);
        paradasIntermedias.add(marmokix);
        paradasIntermedias.add(corrientes);
        paradasIntermedias.add(mozart);
        SentidoRecorrido sentido = tresSiete.getSentidoRecorrido(marmokix, xokas);
        Assertions.assertEquals(paradasIntermedias, tresSiete.getParadasIntermedias(marmokix, xokas, sentido));
    }

    @Test
    public void lineaTiene3ParadasIntermediasDeVuelta(){

        tresSiete.agregarParadas(cortina, marmokix, corrientes, mozart, xokas, lenny_y_Alberdi);
        paradasIntermedias.add(corrientes);
        paradasIntermedias.add(mozart);
        paradasIntermedias.add(xokas);
        SentidoRecorrido sentido = tresSiete.getSentidoRecorrido(xokas, marmokix);
        Assertions.assertEquals(paradasIntermedias, tresSiete.getParadasIntermedias(xokas, marmokix, sentido));
    }

    @Test
    public void recorridoPublicoDeIdaTieneUnaDistancia(){
        tresSiete.agregarParadas(cortina, marmokix, corrientes, mozart, xokas, lenny_y_Alberdi);

        Assertions.assertEquals(400, tramoPublicoDeIda.calcularDistanciaTramo());
    }

    @Test
    public void recorridoPublicoDeVueltaTieneUnaDistancia(){
        tresSiete.agregarParadas(cortina, marmokix, corrientes, mozart, xokas, lenny_y_Alberdi);

        Assertions.assertEquals(300, tramoPublicoDeVuelta.calcularDistanciaTramo());
    }

}

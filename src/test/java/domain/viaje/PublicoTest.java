package domain.viaje;

import domain.viaje.publico.LColectivo;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TPublico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class PublicoTest {
    private Parada cortina, marmokix, corrientes, mozart, xokas, lenny_y_Alberdi;
    private LColectivo tresSiete;
    private List<Parada> paradasIntermedias;
    private Parada unaParada;
    private Parada otraParada;

    private TPublico publicoGenerico;

    @BeforeEach
    void setupThis()  {
        tresSiete = new LColectivo("37");
        unaParada = new Parada("alexelcapo");
        otraParada = new Parada("marmogilazo");
        cortina = new Parada("Cortina");
        marmokix = new Parada("Marmokix");
        corrientes = new Parada("Corrientes");
        mozart = new Parada("Mozart");
        xokas = new Parada("Xokas");
        lenny_y_Alberdi = new Parada("Lenny");
        cortina.setDistanciaSigParada(100);
        marmokix.setDistanciaSigParada(100);
        corrientes.setDistanciaSigParada(100);
        mozart.setDistanciaSigParada(100);

        publicoGenerico = new TPublico(cortina, xokas, tresSiete);

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
    public void lineaTiene3ParadasIntermedias(){

        tresSiete.agregarParadas( cortina, marmokix, corrientes, mozart, xokas, lenny_y_Alberdi);
        paradasIntermedias.add( marmokix);
        paradasIntermedias.add(corrientes);
        paradasIntermedias.add( mozart);
        Assertions.assertEquals(paradasIntermedias, tresSiete.getParadasIntermedias(marmokix, xokas));
    }

    @Test
    public void recorridoPublicoTieneUnaDistancia(){
        tresSiete.agregarParadas( cortina, marmokix, corrientes, mozart, xokas, lenny_y_Alberdi);

        Assertions.assertEquals(400, publicoGenerico.calcularDistanciaTramo());
    }

}

package domain.viaje;


import domain.viaje.publico.LColectivo;
import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;
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

        paradasIntermedias = new ArrayList<Parada>();
    }

    @Test
    public void agregoUnaParada() {

        tresSiete.agregarParadas(unaParada);
        tresSiete.agregarParadas(otraParada);
        Assertions.assertNotNull(unaParada);
        Assertions.assertEquals(unaParada, tresSiete.getParadas().get(0));
    }

    @Test
    public void lineaTiene3Paradas(){
        tresSiete.agregarParadas( cortina, marmokix, corrientes, mozart, xokas, lenny_y_Alberdi);
        paradasIntermedias.add( marmokix);
        paradasIntermedias.add(corrientes);
        paradasIntermedias.add( mozart);
        Assertions.assertEquals(paradasIntermedias, tresSiete.getParadasIntermedias(marmokix, xokas));
    }

}

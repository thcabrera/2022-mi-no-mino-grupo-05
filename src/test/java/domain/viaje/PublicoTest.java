package domain.viaje;


import domain.viaje.publico.LColectivo;
import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;



public class PublicoTest {
    @Test
    public void lineaTiene6Paradas() {
        LColectivo tresSiete = new LColectivo("37");

        Parada cortina = new Parada("Cortina");
        Parada marmokix = new Parada("Marmokix");
        Parada corrientes = new Parada("Corrientes");
        Parada mozart = new Parada("Mozart");
        Parada tito_y_eze = new Parada("tito_y_eze");
        Parada lenny_y_Alberdi = new Parada("lenny");

        List<Parada> paradasIntermedias = new ArrayList<>();
        paradasIntermedias.add(marmokix);
        paradasIntermedias.add(corrientes);
        paradasIntermedias.add(mozart);

        tresSiete.agregarParadas(cortina, marmokix, corrientes, mozart, tito_y_eze, lenny_y_Alberdi);

        // Test de paradas intermedias
        Assert.assertEquals(paradasIntermedias, tresSiete.getParadasIntermedias(marmokix, tito_y_eze));


        // para ver si funcion en assertArrayEquals
        //Assert.assertEquals( [cortina, marmokix, corrientes, mozart, tito_y_eze, lenny_y_Alberdi], tresSiete.getParadas() );
        //Assert.assertArrayEquals([cortina, marmokix, corrientes, mozart, tito_y_eze, lenny_y_Alberdi]);
    }
}

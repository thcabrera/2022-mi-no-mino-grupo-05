package domain.viaje;

import domain.viaje.privado.contratado.TramoContratado;
import domain.viaje.privado.limpio.TramoLimpio;
import domain.viaje.privado.particular.TramoParticular;
import domain.viaje.publico.TramoPublico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DistanciaTrayectoTest {

    private Trayecto trayecto;

    @BeforeEach
    public void setUpThis(){

        Tramo tramoContratado = new TramoContratado();
        tramoContratado.setDistancia(150.0);
        Tramo tramoPublico = new TramoPublico();
        tramoPublico.setDistancia(50.0);
        Tramo tramoParticular = new TramoParticular();
        tramoParticular.setDistancia(800.0);
        Tramo tramoLimpio = new TramoLimpio();
        tramoLimpio.setDistancia(90.0);

        trayecto = new Trayecto();
        trayecto.setTramos(new ArrayList<>(Arrays.asList(tramoContratado, tramoPublico,
                tramoParticular, tramoLimpio)));

    }

    @Test
    public void trayectoCalculaBienSuDistanciaTotal(){
        Assertions.assertEquals(1090.0, trayecto.obtenerDistanciaTotal());
    }

}

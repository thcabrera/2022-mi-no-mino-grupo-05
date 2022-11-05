package domain.calculoHC;

import domain.Direccion;
import domain.entidades.*;
import domain.viaje.Trayecto;
import domain.viaje.privado.particular.Combustible;
import domain.viaje.privado.particular.TramoParticular;
import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TramoPublico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class TrayectosHCTest {
    Parada parada1;
    Parada parada2;
    Parada parada3;
    TramoPublico tramoPublico;
    Linea treintaYCuatro;
    TramoParticular tramoCompartido;
    Persona normal;
    Persona propietario;
    Persona noPropietario;
    Combustible kuchaun;
    Combustible allinol;
    Direccion direccion1;
    Direccion direccion2;
    Provincia bsas;
    Municipio villaSoldati;
    Localidad mataderos;

    @BeforeEach
    public void setupThis(){
        parada1 = new Parada("paradaUno");
        parada2 = new Parada("paradaDos");
        parada3 = new Parada("paradaTres");

        parada1.setDistanciaSigParada(10.0);
        parada2.setDistanciaSigParada(20.0);

        normal = new Persona("Jorge","Lienard",12312323, Documentacion.DNI);
        propietario = new Persona("Lucas", "Panfleto", 132123123, Documentacion.DNI);
        noPropietario = new Persona("Leandro", "Marmovich", 254658, Documentacion.DNI);

        bsas = new Provincia(new HashSet<Municipio>() {
        });
        villaSoldati = new Municipio(new ArrayList<>());

        mataderos = new Localidad(villaSoldati);

        direccion1 = new Direccion("calle1", 1999, mataderos); // hay que cambiarlo,
        direccion2 = new Direccion("calle2", 2354, mataderos);

       // treintaYCuatro = new LColectivo("34", 100.0);
        treintaYCuatro.agregarParadas(parada1, parada2, parada3);
        tramoPublico = new TramoPublico(parada1, parada2, treintaYCuatro);

        allinol = new Combustible();
        
        tramoCompartido = new TramoParticular(allinol, direccion1, direccion2, true);
        tramoCompartido.setPropietario(propietario);
    }

    @Test
    public void calculoHCDeTramoEnTransportePublico(){
        Double hc = tramoPublico.calculoHC(normal);
        Assertions.assertEquals(hc,3000);
    }

    @Test
    public void calculoHCDeTramoCompartidoPropio(){
        Double hc = tramoCompartido.calculoHC(propietario);
        Assertions.assertNotEquals(hc, 0);
    }

    @Test
    public void calculoHCDeTramoCompartidoNoPropio(){
        Double hc = tramoCompartido.calculoHC(noPropietario);
        Assertions.assertEquals(hc, 0);
    }

    @Test
    public void calculoHCDeTrayecto(){
        Trayecto trayecto = new Trayecto(new ArrayList<>());
        trayecto.agregarTramos(tramoPublico,tramoCompartido);

        Double hc = trayecto.calculoHC(noPropietario);
        Assertions.assertEquals(hc, 3000);
    }

}

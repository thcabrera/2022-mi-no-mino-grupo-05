package domain.viaje;

import domain.Direccion;
import domain.entidades.*;
import domain.viaje.privado.contratado.Servicio;
import domain.viaje.privado.contratado.TramoContratado;
import domain.viaje.privado.limpio.TramoLimpio;
import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TipoLinea;
import domain.viaje.publico.TramoPublico;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TramosCompartidosTest {
    private Persona tito, lenny;
    private Organizacion lennySoftware;
    private Area sistemas, ejecutivo, coaching;
    private Direccion dirLennySW = null;
    private Direccion dirMarmoSW = null;
    private TipoOrg empresa = null ;
    private Clasificacion empresaDelSectorSecundario = null;
    private Provincia bsas;
    private Municipio villaSoldati;
    // Tramos
    private TramoPublico casa_a_terminal;
    private TramoContratado terminal_a_org1;
    private TramoLimpio org1_a_org2;
    private Linea linea34;
    private Parada cortina;
    private Parada retiro;

    @BeforeEach
    void setupThis() {
        //  carga de personas y organizacion
        tito = new Persona("Augusto", "Lienard", 43815396, Documentacion.DNI);
        lenny = new Persona("Lenny", "Lecaldare", 43123123, Documentacion.DNI);

        bsas = new Provincia(new HashSet<>());
        villaSoldati = new Municipio(new ArrayList<>());

        dirLennySW = mock(Direccion.class); //new Direccion("MOZART", 1999, bsas, villaSoldati, 241); // soladati.id = 5379
        empresa = new TipoOrg("Empresa");
        empresaDelSectorSecundario = new Clasificacion();
        lennySoftware = new Organizacion("SA", empresa, dirLennySW, empresaDelSectorSecundario);
        sistemas = new Area("Sistemas", lennySoftware);
        ejecutivo = new Area("Ejecutivo", lennySoftware);
        coaching = new Area("Coaching", lennySoftware);

        dirMarmoSW = mock(Direccion.class);

        lenny.solicitarAlta(lennySoftware, ejecutivo);
        lennySoftware.aceptarEmpleado(lenny, ejecutivo);

        tito.solicitarAlta(lennySoftware, coaching);
        lennySoftware.aceptarEmpleado(tito, coaching);

        tito.solicitarAlta(lennySoftware, ejecutivo);
        lennySoftware.aceptarEmpleado(tito, ejecutivo);
        linea34 = new Linea();
        TipoLinea tipoLinea = new TipoLinea();
        tipoLinea.setConsumo(1.0);
        linea34.setTipoLinea(tipoLinea);
        cortina = new Parada("CORTINA");
        cortina.setIndice(0);
        cortina.setDistanciaSigParada(80.0);
        retiro = new Parada("RETIRO");
        retiro.setIndice(1);
        retiro.setDistanciaAntParada(80.0);
        linea34.setParadas(new ArrayList<>(Arrays.asList(cortina, retiro)));
        // carga de trayectos
        casa_a_terminal = new TramoPublico(cortina, retiro, linea34);
        terminal_a_org1 = new TramoContratado(new Servicio("Remis", 1.0), dirMarmoSW, dirLennySW, true);
        terminal_a_org1.setDistancia(90.0);
        terminal_a_org1.setPropietario(lenny);
        org1_a_org2 = new TramoLimpio();
        org1_a_org2.setDistancia(900.0);
    }

    @Test
    public void lennyTiene3Tramos() {
        lenny.darDeAltaTrayecto(casa_a_terminal,terminal_a_org1, org1_a_org2);//Tpublico-TContratado-TLimpio

        Assertions.assertEquals(3, lenny.getTramos().size());

    }

    @Test
    public void lennySoftwareTiene1TramoCompartido() {
        lenny.darDeAltaTrayecto(casa_a_terminal, terminal_a_org1, org1_a_org2);//Tpublico-TContratado-TLimpio

        //Assertions.assertEquals(1, ejecutivo.getMiembros().size() );
        Assertions.assertEquals(1, lennySoftware.getTramosCompartidos().size());
    }
    @Test
    public void titoYLennySeCuentanUnaSolaVez() {
        lenny.darDeAltaTrayecto(casa_a_terminal, terminal_a_org1, org1_a_org2);//Tpublico-TContratado-TLimpio

        Assertions.assertEquals(2, lennySoftware.getMiembros().size() );
    }

    @Test
    public void titoYLennyCompartenTramo() {
        lenny.darDeAltaTrayecto(casa_a_terminal, terminal_a_org1, org1_a_org2);//Tpublico-TContratado-TLimpio
        tito.darDeAltaTrayecto(terminal_a_org1);

        Assertions.assertEquals(1, lennySoftware.getTramosCompartidos().size() );

    }

}

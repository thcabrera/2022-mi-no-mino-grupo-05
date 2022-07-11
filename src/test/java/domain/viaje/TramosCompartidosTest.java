package domain.viaje;

import domain.Direccion;
import domain.entidades.*;
import domain.viaje.privado.contratado.Servicio;
import domain.viaje.privado.contratado.TContratado;
import domain.viaje.privado.contratado.limpio.TLimpio;
import domain.viaje.publico.LColectivo;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TPublico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TramosCompartidosTest {
    private Persona tito, lenny;
    private Organizacion lennySoftware;
    private Area sistemas, ejecutivo, coaching;
    private Direccion dirLennySW = null;
    private Direccion dirMarmoSW = null;
    private TipoOrg empresa = null ;
    private Clasificacion empresaDelSectorSecundario = null;
    // Tramos
    private TPublico casa_a_terminal;
    private TContratado terminal_a_org1;
    private TLimpio org1_a_org2;
    private LColectivo linea34;
    private Parada cortina;
    private Parada retiro;

    @BeforeEach
    void setupThis() {
        //  carga de personas y organizacion
        tito = new Persona("Augusto", "Lienard", 43815396, Documentacion.DNI);
        lenny = new Persona("Lenny", "Lecaldare", 43123123, Documentacion.DNI);

        dirLennySW = new Direccion("MOZART", 1999, "CIUDAD DE BUENOS AIRES", "VILLA SOLDATI", 241); // soladati.id = 5379
        empresa = new TipoOrg("Empresa");
        empresaDelSectorSecundario = new Clasificacion("EmpresaDelSectorSecundario");
        lennySoftware = new Organizacion("SA", empresa, dirLennySW, empresaDelSectorSecundario);
        sistemas = new Area("Sistemas", lennySoftware);
        ejecutivo = new Area("Ejecutivo", lennySoftware);
        coaching = new Area("Coaching", lennySoftware);

        dirMarmoSW = new Direccion("AVENIDA LACARRA", 1500, "CIUDAD DE BUENOS AIRES", "VILLA SOLDATI", 241);

        lenny.solicitarAlta(lennySoftware, ejecutivo);
        lennySoftware.aceptarEmpleado(lenny, ejecutivo);

        tito.solicitarAlta(lennySoftware, coaching);
        lennySoftware.aceptarEmpleado(tito, coaching);

        tito.solicitarAlta(lennySoftware, ejecutivo);
        lennySoftware.aceptarEmpleado(tito, ejecutivo);

        // carga de trayectos
        casa_a_terminal = new TPublico(cortina, retiro, linea34);
        terminal_a_org1 = new TContratado(new Servicio("Remis"), dirMarmoSW, dirLennySW, true);
        terminal_a_org1.setPropietario(lenny);
        org1_a_org2 = new TLimpio("Monopatin", dirLennySW, dirMarmoSW);
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
/*
    @Test
    public void titoYLennyCompartenTramo() {
        lenny.darDeAltaTrayecto(casa_a_terminal, terminal_a_org1, org1_a_org2);//Tpublico-TContratado-TLimpio
        tito.darDeAltaTrayecto(terminal_a_org1);

        Assertions.assertEquals(2, lennySoftware.getTramosCompartidos().size() );

    }
*/
}

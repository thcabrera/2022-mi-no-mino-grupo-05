package domain.viaje;

import domain.Direccion;
import domain.entidades.Persona;
import domain.entidades.Organizacion;
import domain.entidades.Clasificacion;
import domain.entidades.TipoOrg;
import  domain.entidades.Area;
import domain.entidades.Documentacion;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolicitudTest {
    private Persona tito, lenny;
    private Organizacion lennySoftware;
    private Area sistemas, ejecutivo, coaching;
    private Direccion dirLennySW = null;
    private  TipoOrg empresa = null ;
    private Clasificacion empresaDelSectorSecundario = null;

    @BeforeEach
    void setupThis() {
        tito = new Persona("Augusto", "Lienard", 43815396, Documentacion.DNI);
        lenny = new Persona("Lenny", "Lecaldare", 43123123, Documentacion.DNI);

        dirLennySW = new Direccion("mozart", 1999, "CIUDAD DE BUENOS AIRES", "VILLA SOLDATI", 241); // soladati.id = 5379
        empresa = new TipoOrg("Empresa");
        empresaDelSectorSecundario = new Clasificacion("EmpresaDelSectorSecundario");
        lennySoftware = new Organizacion("SA", empresa, dirLennySW, empresaDelSectorSecundario);

        sistemas = new Area("Sistemas", lennySoftware);
        ejecutivo = new Area("Ejecutivo", lennySoftware);
        coaching = new Area("Coaching", lennySoftware);
    }

    @Test
    public void lennySolicitaAltaEnSuOrg() {

        lenny.solicitarAlta(lennySoftware, ejecutivo);

        Assertions.assertEquals(0, lennySoftware.getMiembros().size() );
        Assertions.assertEquals(1, lennySoftware.getSolicitudes().size() );
        Assertions.assertTrue(lenny.getListaAreas().size() == 0 );
    }

    @Test
    public void lennyEsAceptadoPorSuOrg() {
        lenny.solicitarAlta(lennySoftware, ejecutivo);
        lennySoftware.aceptarEmpleado(lenny, ejecutivo);

        Assertions.assertEquals(1, lennySoftware.getMiembros().size() );
        Assertions.assertTrue(lennySoftware.getMiembros().contains(lenny) );

        Assertions.assertTrue(lenny.getListaAreas().contains(ejecutivo));
    }

    @Test
    public void titoYLennySonMiembros() {
        lenny.solicitarAlta(lennySoftware, ejecutivo);
        lennySoftware.aceptarEmpleado(lenny, ejecutivo);

        tito.solicitarAlta(lennySoftware, coaching);
        lennySoftware.aceptarEmpleado(tito, coaching);

        Assertions.assertEquals(1, ejecutivo.getMiembros().size() );
        Assertions.assertEquals(1, coaching.getMiembros().size() );

        Assertions.assertEquals(2, lennySoftware.getMiembros().size() );
        Assertions.assertTrue(lennySoftware.getMiembros().contains(lenny) && lennySoftware.getMiembros().contains(tito)  );
    }

}


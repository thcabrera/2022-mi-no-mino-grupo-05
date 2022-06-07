package domain.viaje;

import com.sun.org.apache.xml.internal.security.keys.storage.implementations.CertsInFilesystemDirectoryResolver;
import domain.Direccion;
import domain.entidades.Solicitud;
import domain.entidades.Persona;
import domain.entidades.Organizacion;
import domain.entidades.Clasificacion;
import domain.entidades.TipoOrg;
import  domain.entidades.Area;
import domain.entidades.Documentacion;


import domain.viaje.publico.LColectivo;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TPublico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.util.List;

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
        lenny = new Persona("Lenny", "Guerrisi", 43123123, Documentacion.DNI);

   //     dirLennySW = new Direccion("mozart", 1999, "Buenos Aires", "comuna 5", "CABA"); // checkearlo para futuros test en los q importe
        empresa = new TipoOrg("Empresa");
        empresaDelSectorSecundario = new Clasificacion("EmpresaDelSectorSecundario");
        lennySoftware = new Organizacion("SA", empresa, dirLennySW, empresaDelSectorSecundario);

        sistemas = new Area(lennySoftware);
        ejecutivo = new Area(lennySoftware);
        coaching = new Area(lennySoftware);
    }
    @Test

    public void fallanLosSetup() {

        Assertions.assertEquals(0,tito.getListaAreas().size() );
    }

    @Test
    public void lennySolicitaAltaEnSuOrg() {

        lenny.solicitarAlta(lennySoftware, ejecutivo);

        Assertions.assertEquals(0, lennySoftware.miembros().size() );
        Assertions.assertEquals(1, lennySoftware.getSolicitudes().size() );
        Assertions.assertTrue(lenny.getListaAreas().size() == 0 );
    }

    @Test
    public void lennyEsAceptadoPorSuOrg() {
        lenny.solicitarAlta(lennySoftware, ejecutivo);
        lennySoftware.aceptarEmpleado(lenny, sistemas);

        Assertions.assertEquals(1, lennySoftware.miembros().size() );
        Assertions.assertTrue(lennySoftware.miembros().contains(lenny) );

        Assertions.assertTrue(lenny.getListaAreas().contains(sistemas));
    }

    @Test
    public void titoYLennySonMiembros() {
        lenny.solicitarAlta(lennySoftware, ejecutivo);
        lennySoftware.aceptarEmpleado(lenny, sistemas);

        tito.solicitarAlta(lennySoftware, coaching);
        lennySoftware.aceptarEmpleado(tito, coaching);

        Assertions.assertEquals(1, sistemas.getMiembros().size() );
        Assertions.assertEquals(1, coaching.getMiembros().size() );

        Assertions.assertEquals(2, lennySoftware.miembros().size() );
        Assertions.assertTrue(lennySoftware.miembros().contains(lenny) && lennySoftware.miembros().contains(tito)  );
    }

}


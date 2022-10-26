package domain;

import domain.entidades.Area;
import domain.entidades.Organizacion;
import domain.entidades.Persona;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonaTest {

    private Organizacion organizacion1;
    private Organizacion organizacion2;
    private Organizacion organizacion3;
    private Area area1;
    private Area area2;
    private Area area3;
    private Area area4;
    private Persona persona;

    @BeforeAll
    public void instanciarOrganizaciones(){
        persona = new Persona();
        organizacion1 = new Organizacion("", null, null, null);
        area1 = new Area();
        area1.setOrganizacion(organizacion1);
        organizacion1.agregarArea(area1);
        area2 = new Area();
        area2.setOrganizacion(organizacion1);
        organizacion1.agregarArea(area2);
        organizacion2 = new Organizacion("", null, null, null);
        area3 = new Area();
        area3.setOrganizacion(organizacion2);
        organizacion2.agregarArea(area3);
        organizacion3 = new Organizacion("", null, null, null);
        area4 = new Area();
        area4.setOrganizacion(organizacion3);
        organizacion3.agregarArea(area4);
        List<Area> areas = Arrays.asList(area1, area2, area3, area4);
        persona.setListaAreas(areas);
    }

    @Test
    public void personaDevuelveBienSusOrganizaciones(){

        Set<Organizacion> organizacionesEsperadas =
                new HashSet<>(Arrays.asList(organizacion1, organizacion2, organizacion3));
        Set<Organizacion> organizacionesReales =
                new HashSet<>(persona.obtenerOrganizaciones());
        Assertions.assertIterableEquals(organizacionesEsperadas, organizacionesReales);

        Assertions.assertEquals(organizacionesEsperadas.size(), persona.obtenerOrganizaciones().size());

    }

}

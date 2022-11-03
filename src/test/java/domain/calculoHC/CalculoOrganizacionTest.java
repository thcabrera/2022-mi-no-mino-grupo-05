package domain.calculoHC;

import domain.Direccion;
import domain.entidades.*;
import domain.viaje.Tramo;
import domain.viaje.Trayecto;
import domain.viaje.privado.contratado.Servicio;
import domain.viaje.privado.contratado.TramoContratado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;

public class CalculoOrganizacionTest {

    private Organizacion organizacion;
    private Area area;
    private Persona persona;
    private Trayecto trayecto;
    private Tramo tramo;

    @BeforeEach
    public void setup(){
        organizacion = new Organizacion(null, null, null, null);
        area = new Area(null, organizacion);
        organizacion.agregarArea(area);
        persona = new Persona();
        persona.agregarArea(area);
        area.agregarMiembro(persona);
        Localidad localidad = new Localidad(8, null);
        Direccion direccionInicio = new Direccion("Baigorria", 3057, localidad);
        Direccion direccionFin = new Direccion("Basualdo", 1961, localidad);
        Servicio servicio = new Servicio(null, 1.5);
        tramo = new TramoContratado(servicio, direccionInicio, direccionFin, false, persona);
        tramo.setDistancia(100.0);
        trayecto = new Trayecto();
        trayecto.setPersona(persona);
        trayecto.setOrganizacion(organizacion);
        trayecto.setTramos(new ArrayList<>(Collections.singletonList(tramo)));
        persona.setTrayectos(new ArrayList<>(Collections.singletonList(trayecto)));
    }

    private LocalDate obtenerFechaActual(){
        return LocalDate.now(ZoneId.of("America/Argentina/Buenos_Aires"));
    }

    @Test
    public void tramoCalculaBienSuHC(){
        // si el tramo tiene distancia = 100.0 y el servicio FE = 1.5 => HC del tramo = 150.0
        Assertions.assertEquals(150.0, tramo.calculoHC(persona),
                "Falla el calculo de HC del tramo");
    }

    @Test
    public void trayectoCalculaBienSuHC(){
        // tiene un solo tramo, por lo que HC del trayecto = HC del tramo = 150.0
        Assertions.assertEquals(150.0, trayecto.calculoHC(persona),
                "Falla el calculo de HC del trayecto");
    }

    @Test
    public void areaCalculaBienSuHC(){
        Assertions.assertEquals(150.0, area.calculoHC(),
                "Falla el cálculo de HC del área");
    }

    @Test
    public void organizacionCreaHCYLaDevuelve(){
        HuellaDeCarbono huellaDeCarbono = organizacion.obtenerHCActual();
        Assertions.assertEquals(organizacion, huellaDeCarbono.getOrganizacion(),
                "Falla la organización de la HC");
        Assertions.assertEquals(150.0, huellaDeCarbono.getValor(),
                "Falla el valor de la HC");
        Assertions.assertEquals(obtenerFechaActual(), huellaDeCarbono.getFechaMedicion(),
                "Falla la fecha de la HC");
    }

    @Test
    public void organizacionDevuelveHCExistente(){
        // una vez ya creada la huella de carbono, la devuelve siempre que sea la última
        HuellaDeCarbono huellaDeCarbono = organizacion.obtenerHCActual();
        Assertions.assertEquals(huellaDeCarbono, organizacion.obtenerHCActual(),
                "Org no responde con HC existentes");
    }

}

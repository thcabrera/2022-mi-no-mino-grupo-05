package domain.calculoHC;

import domain.Direccion;
import domain.entidades.*;
import domain.mediciones.consumos.Periodicidad;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AgenteSectorialTest {


    Municipio municipioLaMatanza, municipioMoron;
    AgenteSectorial agenteMunicipal, agenteSectorial;
    Provincia provinciaBuenosAires;
    Provincia bsas = new Provincia(new ArrayList<>());
    Direccion dirTitoSW = new Direccion("mozart", 1999, bsas, municipioLaMatanza, 241); // soladati.id = 5379
    TipoOrg empresa = new TipoOrg("Empresa");
    Clasificacion empresaDelSectorSecundario = new Clasificacion();
    Organizacion titoSW = new Organizacion("SA", empresa, dirTitoSW, empresaDelSectorSecundario);

    @BeforeEach
    public void setupThis(){

        ArrayList<Organizacion> organizacionesDeLaMatanza = new ArrayList<>();
        ArrayList<Organizacion> organizacionesDeMoron = new ArrayList<>();
        municipioLaMatanza = new Municipio(organizacionesDeLaMatanza);
        municipioMoron = new Municipio(organizacionesDeMoron);

        ArrayList<Municipio> municipios = new ArrayList<>();
        municipios.add(municipioLaMatanza);
        municipios.add(municipioMoron);
        provinciaBuenosAires = new Provincia(municipios);
    }

    @Test
    public void calculoHC() {

        AgenteSectorial agenteMunicipal = new AgenteSectorial(municipioLaMatanza);


    }
}



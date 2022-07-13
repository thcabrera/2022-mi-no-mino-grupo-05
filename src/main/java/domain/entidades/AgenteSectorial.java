package domain.entidades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;

// si el agente tiene una provincia, el calculo de HC será la suma del calculo de HC de sus municipios
// si el agente tiene un municipio, el calculo de HC será la suma del calculo de HC de sus organizaciones
public class AgenteSectorial {

    @Getter
    @Setter
    private Sector sector;

    public AgenteSectorial(Sector sector) {
        setSector(sector);
    }

    public Double calculoHC(Periodicidad periodo){
        return this.sector.calculoHC(periodo);
    }

}

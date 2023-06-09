package domain.entidades;

import domain.mediciones.consumos.Periodicidad;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

// si el agente tiene una provincia, el calculo de HC será la suma del calculo de HC de sus municipios
// si el agente tiene un municipio, el calculo de HC será la suma del calculo de HC de sus organizaciones
@Entity
@Table(name = "agente_sectorial")
public class AgenteSectorial extends Actor{

//    @Id
//    @GeneratedValue
//    private Integer id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "sector_id", referencedColumnName = "id")
    private Sector sector;

    public AgenteSectorial(Sector sector) {
        setSector(sector);
    }

    public AgenteSectorial() {

    }


    public Double calculoHC(Integer anio, Integer mes){
        return this.sector.calculoHC(anio, mes);
    }

}

package domain.entidades;

import domain.conversores.LocalDateAttributeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "huella_de_carbono")

public class HuellaDeCarbono {

    @Id
    @GeneratedValue
    private Integer id;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "fecha_medicion")
    private LocalDate fechaMedicion;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organizacion organizacion;

    public HuellaDeCarbono(Double valor, Organizacion organizacion){
        setValor(valor);
        setOrganizacion(organizacion);
        setFechaMedicion(LocalDate.now(ZoneId.of("America/Argentina/Buenos_Aires")));
    }

}

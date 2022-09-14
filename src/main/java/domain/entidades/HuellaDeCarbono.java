package domain.entidades;

import domain.conversores.LocalDateAttributeConverter;
import javax.persistence.*;
import java.time.LocalDate;

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

}

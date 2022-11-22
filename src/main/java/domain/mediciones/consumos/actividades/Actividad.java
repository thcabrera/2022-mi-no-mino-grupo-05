package domain.mediciones.consumos.actividades;

import domain.entidades.Organizacion;
import domain.mediciones.consumos.Periodicidad;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "actividad")
@DiscriminatorColumn(name = "discriminador", length = 18)
public abstract class Actividad {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "mes")
    private Integer mes = null;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organizacion organizacion;

    public Actividad(Integer anio, Integer mes){
        this.anio = anio;
        this.mes = mes;
    }

    public abstract Double calculoHC(Integer anio, Integer mes);

    public boolean creadaEntre(Integer anio, Integer mes) {
        return Periodicidad.coincide(anio, mes, getAnio(), getMes());
    }

}

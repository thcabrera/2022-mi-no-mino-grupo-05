package domain.viaje.privado.contratado;

import lombok.*;
import org.checkerframework.checker.interning.qual.EqualsMethod;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name="tipo_servicio")
public class Servicio {
    @Id
    @GeneratedValue
    private Integer id;

    //  ----------  GETTERS & SETTERS  ----------
    @Column(name="descripcion")
    private String nombre;

    @Column(name = "consumo")
    private Double consumoPorKM ;


    public Servicio(String nombre){
        this.nombre = nombre;
    }
    public Servicio(String nombre, Double consumo ){
        this.nombre = nombre;
        this.consumoPorKM = consumo;
    }

    public String toString(){
        return nombre;
    }

}

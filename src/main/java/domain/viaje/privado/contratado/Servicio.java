package domain.viaje.privado.contratado;

import javax.persistence.*;

@Entity
@Table(name="tipo_servicio")
public class Servicio {
    @Id
    @GeneratedValue
    private Integer id;

    //  ----------  GETTERS & SETTERS  ----------
    @Column(name="descripcion")
    private String nombre;

    public Servicio(String nombre){
        this.nombre = nombre;
    }

    public String toString(){
        return nombre;
    }

}

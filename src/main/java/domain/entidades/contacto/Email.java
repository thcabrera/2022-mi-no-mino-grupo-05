package domain.entidades.contacto;

import domain.services.envioCorreo.ServicioCorreo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@DiscriminatorValue(value = "Email")
public class Email extends Contacto{

    private String correo;

    public Email(String correo){
        this.correo = correo;
    }

    public void notificar(Mensaje mensaje) {
        //todo
        ServicioCorreo correo = new ServicioCorreo();
        //Mensaje mensaje = new Mensaje();

        correo.enviarCorreo(this.correo, mensaje.getAsunto(), mensaje.getCuerpo());

    }

}

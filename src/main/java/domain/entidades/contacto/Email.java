package domain.entidades.contacto;

import domain.mediciones.services.envioCorreo.ServicioCorreo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@DiscriminatorValue(value = "Email")
public class Email extends Contacto{

    public Email(String correo){
        this.contacto = correo;
    }

    public void notificar(Mensaje mensaje) {
        ServicioCorreo.enviarCorreo(this.contacto, mensaje.getAsunto(), mensaje.getCuerpo());
    }

}

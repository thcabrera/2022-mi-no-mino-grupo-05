package domain.entidades.contacto;

import domain.services.envioCorreo.ServicioCorreo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Email implements Contacto{

    private String correo;

    public void notificar() {
        //todo
        ServicioCorreo correo = new ServicioCorreo();
        Mensaje mensaje = new Mensaje();

        correo.enviarCorreo(this.correo, mensaje.getAsunto(), mensaje.getCuerpo());

    }

}

package domain.entidades.contacto;

import domain.services.envioCorreo.ServicioCorreo;

import java.util.List;

public class Email implements Contacto{

    private String correo;

    public void notificar() {
        //todo
        ServicioCorreo correo = new ServicioCorreo();
        Mensaje mensaje = new Mensaje();

        correo.enviarCorreo(this.correo, mensaje.getAsunto(), mensaje.getCuerpo());

    }

}

package domain.entidades.contacto;

import domain.services.envioCorreo.ServicioCorreo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email implements Contacto{

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

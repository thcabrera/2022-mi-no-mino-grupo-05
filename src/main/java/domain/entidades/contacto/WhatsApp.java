package domain.entidades.contacto;

import domain.mediciones.services.whatsApp.WhatsappConcreteAdapter;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "WhatsApp")
public class WhatsApp extends Contacto {

    public WhatsApp(String contacto){
        this.contacto = contacto;
    }

    public void notificar(Mensaje mensaje){
        new WhatsappConcreteAdapter().enviarMensaje(this.contacto, mensaje);
    }
}

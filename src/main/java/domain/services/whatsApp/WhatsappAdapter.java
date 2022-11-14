package domain.services.whatsApp;

import domain.entidades.contacto.Mensaje;

public interface WhatsappAdapter {

    void enviarMensaje(String telefono, Mensaje mensaje);

}

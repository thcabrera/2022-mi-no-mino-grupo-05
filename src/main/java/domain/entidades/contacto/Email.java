package domain.entidades.contacto;

import domain.entidades.Organizacion;

import java.util.List;

public class Email implements Contacto{

    private List<Organizacion> organizaciones;

    public void notificar() {
        //todo
        //this.organizaciones.forEach(o -> enviar(o.getContactos()));

    }

    public void enviar(Organizacion organizacion){
        // TODO
    }
}

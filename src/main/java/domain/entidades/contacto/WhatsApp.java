package domain.entidades.contacto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "WhatsApp")
public class WhatsApp extends Contacto {

    private String numero;



    public WhatsApp(String numero){
        this.numero = numero;
    }


    public void notificar(Mensaje mensaje){
        //todo
    }
}

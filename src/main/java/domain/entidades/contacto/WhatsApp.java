package domain.entidades.contacto;

public class WhatsApp implements Contacto {

    private String numero;

    public WhatsApp(String numero){
        this.numero = numero;
    }


    public void notificar(Mensaje mensaje){
        //todo
    }
}

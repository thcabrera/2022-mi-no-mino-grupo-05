package domain;


import domain.entidades.*;
import domain.entidades.contacto.Contacto;
import domain.entidades.contacto.Email;
import domain.entidades.contacto.Mensaje;
import domain.services.envioCorreo.ServicioCorreo;
import domain.viaje.privado.contratado.TContratado;
import domain.viaje.privado.contratado.limpio.TLimpio;
import domain.viaje.publico.LColectivo;
import domain.viaje.publico.Parada;
import domain.viaje.publico.TPublico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnviarCorreoTest {

    private String destinatario;
    private String asunto;
    private String cuerpo;


    @BeforeEach
    public void init(){
        destinatario =  "ezequielmalfonso@gmail.com"; //A quien le quieres escribir.
        asunto = "Correo de prueba enviado desde Java";
        cuerpo = "A continuación se deja link de acceso a recomendaciones: \n" +
                " https://www.huellaCarbono.org.ar/recomendaciones.html";

   }

    @Test
    public void envioDeCorreoTest(){
        ServicioCorreo envio = new ServicioCorreo();
        Mensaje mensaje = new Mensaje();

        mensaje.setAsunto(asunto);
        mensaje.setCuerpo(cuerpo);

        System.out.println(mensaje.getCuerpo());
        System.out.println(mensaje.getAsunto());

        envio.enviarCorreo( this.destinatario, mensaje.getAsunto(), mensaje.getCuerpo());


    }



}
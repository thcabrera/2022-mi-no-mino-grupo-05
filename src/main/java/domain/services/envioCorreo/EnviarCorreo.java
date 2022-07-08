package domain.services.envioCorreo;

import javax.mail.Address;

import static domain.services.envioCorreo.ConfiguracionServidor.enviarConGMail;


public class EnviarCorreo {
    public static void main(String[] args) {
      String destinatario =  "ezequielmalfonso@gmail.com"; //A quien le quieres escribir.
        String asunto = "Correo de prueba enviado desde Java";
        String cuerpo = "Esta es una prueba de correo...";

        enviarConGMail(destinatario, asunto, cuerpo);

    }


}

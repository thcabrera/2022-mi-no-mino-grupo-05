package domain.services.envioCorreo;

import static domain.services.envioCorreo.ConfiguracionServidorCorreo.enviarCorreo;


public class EnviarCorreo {
    public static void main(String[] args) {
      String destinatario =  "ezequielmalfonso@gmail.com"; //A quien le quieres escribir.
        String asunto = "Correo de prueba enviado desde Java";
        String cuerpo = "Esta es una prueba de correo...";

        enviarCorreo(destinatario, asunto, cuerpo);

    }


}

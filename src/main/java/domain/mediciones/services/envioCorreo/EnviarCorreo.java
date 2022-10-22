package domain.mediciones.services.envioCorreo;


public class EnviarCorreo {
    public static void main(String[] args) {
      String destinatario =  "ezequielmalfonso@gmail.com"; //A quien le quieres escribir.
        String asunto = "Correo de prueba enviado desde Java";
        String cuerpo = "Esta es una prueba de correo...";

        ServicioCorreo.enviarCorreo(destinatario, asunto, cuerpo);

    }


}

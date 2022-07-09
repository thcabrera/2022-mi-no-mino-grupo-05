package domain;

import domain.services.envioCorreo.ConfiguracionServidorCorreo;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static domain.services.envioCorreo.ConfiguracionServidorCorreo.enviarCorreo;

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

        enviarCorreo(destinatario, asunto, cuerpo);

    }



}

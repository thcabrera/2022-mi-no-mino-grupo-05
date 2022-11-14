package domain;


import domain.entidades.*;
import domain.entidades.contacto.Email;
import domain.entidades.contacto.Mensaje;
import domain.services.envioCorreo.ServicioCorreo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import java.util.HashSet;

public class EnviarCorreoTest {

    private String destinatario;
    private String asunto;
    private String cuerpo;

    private Email destinatario2;
    private Email destinatario3;
    private Email destinatario4;
    private Email fedePrandiGod;

    Municipio municipioLaMatanza, municipioMoron;
    AgenteSectorial agenteMunicipal, agenteSectorial;
    Provincia provinciaBuenosAires;
    Provincia bsas = new Provincia(new HashSet<>());
    Direccion dirTitoSW = mock(Direccion.class); //new Direccion("mozart", 1999, bsas, municipioLaMatanza, 241); // soladati.id = 5379
    TipoOrg empresa = new TipoOrg("Empresa");
    Clasificacion empresaDelSectorSecundario = new Clasificacion();
    Organizacion titoSW = new Organizacion("SA", empresa, dirTitoSW, empresaDelSectorSecundario);

    @BeforeEach
    public void init() {
        destinatario = "tcabreralavezzi@frba.utn.edu.ar"; //A quien le quieres escribir.
        asunto = "Correo de prueba enviado desde Java";
        cuerpo = "A continuación se deja link de acceso a recomendaciones: \n" +
                " https://www.huellaCarbono.org.ar/recomendaciones.html";
//        destinatario2 = new Email("lmarmo@frba.utn.edu.ar");
//        destinatario3 = new Email("llienardguerrisi@frba.utn.edu.ar");
        destinatario4 = new Email("tcabreralavezzi@frba.utn.edu.ar");
//        fedePrandiGod = new Email("fprandi@frba.utn.edu.ar");
//        titoSW.agregarContactos(destinatario2, destinatario3, destinatario4, fedePrandiGod);
        titoSW.agregarContactos(destinatario4);


    }


    @Test
    public void envioDeCorreoTest() {
        ServicioCorreo envio = new ServicioCorreo();
        Mensaje mensaje = new Mensaje();

        mensaje.setAsunto(asunto);
        mensaje.setCuerpo(cuerpo);

        envio.enviarCorreo(this.destinatario, mensaje.getAsunto(), mensaje.getCuerpo());

    }

    @Test
    public void envioDeCorreoATodosLosContactosTest() {
        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto(asunto);
        mensaje.setCuerpo(cuerpo);

        titoSW.notificar(mensaje);
    }
}

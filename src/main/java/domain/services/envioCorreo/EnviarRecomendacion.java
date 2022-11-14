package domain.services.envioCorreo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class EnviarRecomendacion implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        // TODO deberiamos hacer un forEach a todos los contactos que se cargaron en la BD
        // TODO deberíamos enviar algo diferente dependiendo del tipo de contacto
        String destinatario =  "thiagomartincabreralavezzi@gmail.com"; //A quien le quieres escribir.
        String asunto = "Correo de prueba enviado desde DDS Dream Team";
        String cuerpo = "Esta es una prueba de correo configurado por CRON...";

        ServicioCorreo.enviarCorreo(destinatario, asunto, cuerpo);
        System.out.println("ENVIANDO RECOMENDACIONES!!!");
    }
}

package tareasProgramadas;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

import static domain.services.envioCorreo.ConfiguracionServidorCorreo.enviarCorreo;

public class QuartzJob implements Job {
    // En este no entendi mucho la explicacion estaba en un ingles muy de mierda
   /* public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("Quartz Job Key " + jobKey);
    }*/

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        String destinatario =  "ezequielmalfonso@gmail.com"; //A quien le quieres escribir.
        String asunto = "Correo de prueba enviado desde Java";
        String cuerpo = "Esta es una prueba de correo...";

        // ==> Acá va la clase q tenemos que instanciar para que comience el envio de correo y ws
        //enviarCorreo(destinatario, asunto, cuerpo);

        System.out.println("TAREA 1: " + new Date());
       // JobKey jobKey = jec.getJobDetail().getKey();
       // System.out.println("Quartz Job Key " + jobKey);
    }
}
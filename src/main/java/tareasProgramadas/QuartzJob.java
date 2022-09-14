package tareasProgramadas;

import domain.entidades.Organizacion;
import domain.entidades.contacto.Mensaje;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;

public class QuartzJob implements Job {
    // En este no entendi mucho la explicacion estaba en un ingles muy de mierda
   /* public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("Quartz Job Key " + jobKey);
    }*/

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {

     //   String destinatario =  "ezequielmalfonso@gmail.com"; //A quien le quieres escribir.
        String asunto = "Correo de prueba enviado desde Java";
        String cuerpo = "Esta es una prueba de correo...";

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto(asunto);
        mensaje.setCuerpo(cuerpo);

        ArrayList<Organizacion> organizaciones = new ArrayList<>();
        //Bajar las organizaciones de la DB
        organizaciones.forEach(org -> org.notificar(mensaje));

    }
}
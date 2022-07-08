package tareasProgramadas;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

public class QuartzJob implements Job {
    // En este no entendi mucho la explicacion estaba en un ingles muy de mierda
   /* public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("Quartz Job Key " + jobKey);
    }*/

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("TAREA 1: " + new Date());   // ==> Acá va la clase q tenemos que instanciar para que comeince el envio de correo y ws
        JobKey jobKey = jec.getJobDetail().getKey();
        System.out.println("Quartz Job Key " + jobKey);
    }
}
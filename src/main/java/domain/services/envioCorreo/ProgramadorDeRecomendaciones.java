package domain.services.envioCorreo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class ProgramadorDeRecomendaciones {

    public static void programar(){
        JobDetail jobDetail = JobBuilder.newJob(EnviarRecomendacion.class).build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("Recomendaciones")
                .withSchedule(CronScheduleBuilder.atHourAndMinuteOnGivenDaysOfWeek(23, 20, 1))
                .build();
        try{
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

    }

}

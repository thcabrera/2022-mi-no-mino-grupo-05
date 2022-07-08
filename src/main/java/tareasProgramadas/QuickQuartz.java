package tareasProgramadas;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.TriggerBuilder.newTrigger;

public class QuickQuartz {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // start the Scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // create the Job
        JobDetail job = JobBuilder.newJob(QuartzJob.class).
                withIdentity("aca repodrido", "Hola que tal!!!!!!").build();

        // Create SimpleTrigger  ==> Acá crea un trigger simple sin schedule, hay que hacer desde codigo la
        //                           periodicidad y controlar con
        // create the Schedule, run every 5 seconds
       /* ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.
                simpleSchedule().
                withIntervalInSeconds(5).
                repeatForever();
        */

     /*   Trigger trigger = TriggerBuilder.
                newTrigger().
                withIdentity("TriggerName", "Group1").
                withSchedule(scheduleBuilder).
                startNow().build();
*/
        // Create CronTrigger  ==> aca indico cuando arranca
        // Cron para cada segundo "0/1 * * * * ?"
        // "s m H dM M dW y"
        Trigger trigger = newTrigger()
                      .withIdentity("TriggerName", "Group2")
                      .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                       .build();

        // schedule the Job
        scheduler.scheduleJob(job, trigger);
        scheduler.start();

        // run for 30 seconds and exit
        Thread.sleep(1000 * 10);

        // Acá para de repetirse
        scheduler.shutdown();
    }
}
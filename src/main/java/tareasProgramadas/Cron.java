package tareasProgramadas;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.TriggerBuilder.newTrigger;

public class Cron {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // start the Scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // create the Job
        JobDetail job = JobBuilder.newJob(QuartzJob.class).build();
                //withIdentity("aca repodrido", "Hola que tal!!!!!!").build();

        // Create CronTrigger  ==> aca indico cuando arranca
        // Cron para cada segundo "0/1 * * * * ?"
        // "s m H dM M dW y"
        Trigger trigger = newTrigger()
                .withIdentity("TriggerName", "Group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        // schedule the Job
        scheduler.scheduleJob(job, trigger);
        scheduler.start();

        // run for 30 seconds and exit
        // Thread.sleep(1000 * 10);

        // Acá para de repetirse
        // scheduler.shutdown();
    }
}
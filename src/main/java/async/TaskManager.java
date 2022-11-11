package async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// maneja las tareas concurrentemente con 4 hilos como máximo, teniendo colas de tareas
public class TaskManager {

    private static TaskManager taskManager;
    private final ExecutorService executorService;

    public static TaskManager obtenerTaskManager(){
        if (taskManager == null){
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    private TaskManager() {
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public void agregarALaCola(Runnable tarea) {
        this.executorService.execute(tarea);
    }

}

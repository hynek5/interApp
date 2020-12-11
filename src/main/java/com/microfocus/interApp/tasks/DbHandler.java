package com.microfocus.interApp.tasks;

import com.microfocus.interApp.domain.Task;
import com.microfocus.interApp.services.TaskService;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static com.microfocus.interApp.InterAppApplication.logger;

@Component
public class DbHandler extends Handler implements Runnable {

   private TaskService service;
   private Integer handleInterval;

    public DbHandler(TaskService service, Integer handleInterval) {
        this.service = service;
        this.handleInterval = handleInterval;
    }

   public DbHandler() {}

    @Override
    public void run() {
        while (true) {
            try {
                handleNewTask();
                handleCompletedTasks();
                Thread.sleep(handleInterval);
            } catch (Exception e) {
                logger.error("Error", e);
            }
        }
    }

    public void handleCompletedTasks() {
        Task completedTask = completedTasks.poll();
        if (completedTask != null) {
            logger.debug("Setting task UUID:{} as completed", completedTask.getUuid());
            completedTask.setStatus(Task.Status.COMPLETED);
            service.insertToDb(completedTask);
            handleCompletedTasks();
        }
    }

    @Override
    public synchronized void handleRunningTask(Task task) {
        //setting task to running status
        service.insertToDb(task);
    }

    @Override
    public void setCurrentManager(TaskManager manager) {

    }

    public void handleNewTask() {
        Stream<Task> newTasks = service.findNewTasks();
        if (newTasks != null) {
            newTasks.forEach(task -> {
                logger.debug("Setting task UUID: {} as pending",task.getUuid());
                task.setStatus(Task.Status.PENDING);
                service.insertToDb(task);
                pendingTasks.add(task);
            });
        }
    }

    @Deprecated
    @Override
    public void handleCompletedTask(Task task) {
    }

}

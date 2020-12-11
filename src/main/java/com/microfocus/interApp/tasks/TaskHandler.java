package com.microfocus.interApp.tasks;

import com.microfocus.interApp.domain.Task;

import static com.microfocus.interApp.InterAppApplication.logger;
import static com.microfocus.interApp.domain.Task.Status.*;

public class TaskHandler extends Handler implements Runnable{

    private Critera usedCritera;
    private TaskManager currentManager;

    public TaskHandler(Critera critera) {
        usedCritera = critera;
    }

    public void setCurrentManager(TaskManager currentManager) {
        this.currentManager = currentManager;
    }

    @Override
    public void run() {
        while (true) {
            handleNewTask();
        }
    }

    @Override
    public void handleNewTask() {
        Task taskToHandle =  pendingTasks.peek();
        if (taskToHandle == null){
            return;
        }
        if (usedCritera.compareKeys(usedCritera.generateKey(taskToHandle))) {
            //removing since task passed critera
            pendingTasks.remove();
            handleRunningTask(taskToHandle);
        }

    }

    @Deprecated
    @Override
    public void handleCompletedTasks() {
    //not used
    }

    @Override
    public synchronized void handleCompletedTask(Task task) {
        usedCritera.updateCriteria(task);
        logger.debug("Task handler is adding completed task:{} to completed queue",task.getUuid());
        completedTasks.add(task);

    }

    @Override
    public void handleRunningTask(Task task) {
        logger.debug("Setting task with UUID:{} to Running",task.getUuid());
        task.setStatus(RUNNING);
        currentManager.notifyDbHandler(task);
        Thread taskThread = new Thread(new RunnableTask(()-> {
            handleCompletedTask(task);
        }, task));
        taskThread.start();
    }

}

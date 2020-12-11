package com.microfocus.interApp.tasks;

import com.microfocus.interApp.domain.Task;

import static com.microfocus.interApp.InterAppApplication.logger;

import java.util.LinkedList;
import java.util.Queue;

public class TaskManager<T extends Handler> implements Runnable {

    Queue<Task> pendingTasks = new LinkedList<>();
    Queue<Task> completedTasks = new LinkedList<>();
    private T dbHandler;
    T taskHandler;


    public TaskManager(T dbHandler, T taskHandler)  {
        Handler.init(completedTasks, pendingTasks);
        this.dbHandler = dbHandler;
        this.taskHandler = taskHandler;
        taskHandler.setCurrentManager(this);
        logger.info("init task handler");

    }

    @Override
    public void run() {
        Thread dbHandlerThread = new Thread(dbHandler);
        dbHandlerThread.start();
        Thread taskHandlerThread = new Thread(taskHandler);
        taskHandlerThread.start();
        while (true) {
            //keep manager running
        }
    }

    public void notifyDbHandler(Task task) {
        dbHandler.handleRunningTask(task);
    }

}


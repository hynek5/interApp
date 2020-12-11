package com.microfocus.interApp.tasks;

import com.microfocus.interApp.domain.Task;

import static com.microfocus.interApp.InterAppApplication.logger;

public class RunnableTask implements Runnable {

    Task originalTask;
    public interface Callback {
        void taskDone();
    }

    private final Callback callback;

    public RunnableTask(Callback callback, Task task) {
        this.callback = callback;
        this.originalTask = task;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(15000);
            callback.taskDone();
        } catch (InterruptedException e) {
            logger.error("When running task:"+ originalTask+ "exception:",e);
        }


    }
}

package com.microfocus.interApp.tasks;

import com.microfocus.interApp.domain.Task;

import java.util.Queue;

public abstract class Handler implements IHandler,Runnable {

    static Queue<Task> pendingTasks;
    static Queue<Task> completedTasks;

    public static void init(Queue<Task> completedTasks, Queue<Task> pendingTasks) {
        Handler.completedTasks = completedTasks;
        Handler.pendingTasks = pendingTasks;
    }


}

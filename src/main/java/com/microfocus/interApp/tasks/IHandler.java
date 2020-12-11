package com.microfocus.interApp.tasks;

import com.microfocus.interApp.domain.Task;

public interface IHandler {

    void handleNewTask();
    void handleCompletedTasks();
    void handleCompletedTask(Task task);
    void handleRunningTask(Task task);
    void setCurrentManager(TaskManager manager);
}

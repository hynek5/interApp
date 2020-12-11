package com.microfocus.interApp.bootstrap;

import com.microfocus.interApp.services.TaskService;
import com.microfocus.interApp.tasks.DefaultCriteria;
import com.microfocus.interApp.tasks.TaskHandler;
import com.microfocus.interApp.tasks.TaskManager;
import com.microfocus.interApp.tasks.DbHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskHandlerRunner implements CommandLineRunner {

    private final TaskService service;
    private TaskManager taskManager;

    public TaskHandlerRunner(TaskService service) {
        this.service = service;
    }

    @Override
    public void run(String... args)  {
        taskManager = new TaskManager(
                new DbHandler(service, 5000),
                new TaskHandler(new DefaultCriteria()));
        taskManager.run();
    }
}

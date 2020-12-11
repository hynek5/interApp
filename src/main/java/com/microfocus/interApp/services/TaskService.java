package com.microfocus.interApp.services;

import com.microfocus.interApp.domain.Task;
import com.microfocus.interApp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class TaskService implements DataService<Task> {

    @Autowired
    TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskService() {

    }

    @Override
    public String insertToDb(Task entity) {
        repository.save(entity);
        return "Task has been created and queued";
    }

    public Iterable<Task> selectAllTasks() {
        return repository.findAll();
    }

    public Stream<Task> findNewTasks() {
        Stream<Task> newTasks = null;
        // try {
        newTasks = repository.findByStatus(Task.Status.NEW).stream();//.forEach(task -> logger.info("task id: {}",task.getId()));

        return newTasks;
        // }
    }
}

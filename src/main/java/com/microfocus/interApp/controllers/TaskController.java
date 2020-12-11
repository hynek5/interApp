package com.microfocus.interApp.controllers;

import com.microfocus.interApp.domain.Task;
import com.microfocus.interApp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping(value = "/createTask")
    public String createTaskForm(Model model) {
        model.addAttribute("task",new Task());

        return "tasks/taskCreation";
    }

    @GetMapping(value = "/listTasks")
    public String listTasks(Model model) {
        List<Task> tasks = new ArrayList<>();
        service.selectAllTasks().forEach(task -> tasks.add(task));
        model.addAttribute("tasks",tasks);
        return "tasks/existingTasks";
    }

    @PostMapping(value = "/submitTask")
    public String submitTask(@ModelAttribute("task")Task toSubmit, Model model) {
        model.addAttribute("message",service.insertToDb(toSubmit));
        return "result";
    }




}

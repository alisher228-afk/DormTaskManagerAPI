package org.example.dormtaskmanagerapi.controller;

import org.example.dormtaskmanagerapi.entity.Task;
import org.example.dormtaskmanagerapi.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
    @DeleteMapping("/delete/{id}")
    public Task deleteTaskById(@PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }
}

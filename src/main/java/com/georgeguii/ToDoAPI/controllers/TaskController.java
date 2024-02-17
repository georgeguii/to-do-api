package com.georgeguii.ToDoAPI.controllers;

import com.georgeguii.ToDoAPI.entities.Task;
import com.georgeguii.ToDoAPI.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        var result = taskService.getAll();
        return ResponseEntity.ok(result);
    }
}

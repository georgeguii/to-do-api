package com.georgeguii.ToDoAPI.services;

import com.georgeguii.ToDoAPI.entities.Task;
import com.georgeguii.ToDoAPI.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(UUID id) {
        return taskRepository.getReferenceById(id);
    }
}

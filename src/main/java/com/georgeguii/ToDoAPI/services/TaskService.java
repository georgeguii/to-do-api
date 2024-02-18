package com.georgeguii.ToDoAPI.services;

import com.georgeguii.ToDoAPI.dtos.GetAllTaskResponseDTO;
import com.georgeguii.ToDoAPI.dtos.GetTaskByIdResponseDTO;
import com.georgeguii.ToDoAPI.dtos.TaskRequestDTO;
import com.georgeguii.ToDoAPI.entities.Task;
import com.georgeguii.ToDoAPI.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<GetAllTaskResponseDTO> getAll() {
        return taskRepository.findAll().stream().map(GetAllTaskResponseDTO::new).toList();
    }

    public GetTaskByIdResponseDTO getById(UUID id) {
        Optional<Task> result = taskRepository.findById(id);
        return result.map(GetTaskByIdResponseDTO::new).orElse(null);
    }

    public UUID create(TaskRequestDTO requestDTO) {
        var task = new Task(requestDTO);
        taskRepository.save(task);
        return task.getId();
    }


}

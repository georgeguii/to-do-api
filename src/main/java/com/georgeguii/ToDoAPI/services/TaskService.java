package com.georgeguii.ToDoAPI.services;

import com.georgeguii.ToDoAPI.dtos.GetAllTaskResponseDTO;
import com.georgeguii.ToDoAPI.dtos.GetTaskByIdResponseDTO;
import com.georgeguii.ToDoAPI.dtos.TaskRequestDTO;
import com.georgeguii.ToDoAPI.entities.Task;
import com.georgeguii.ToDoAPI.repositories.TaskRepository;
import io.micrometer.common.util.StringUtils;
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

    public GetTaskByIdResponseDTO update(UUID id, TaskRequestDTO requestDTO) {
        if(StringUtils.isBlank(requestDTO.title()) && StringUtils.isBlank(requestDTO.description())) {
            throw new IllegalArgumentException("É necessário atualizar ao menos um campo (título ou descrição)");
        }

        var result = taskRepository.findById(id);
        if(result.isEmpty()) { return null; }

        Task task = result.get();

        if(!StringUtils.isBlank(requestDTO.title())) {
            task.setTitle(requestDTO.title());
        }

        if(!StringUtils.isBlank(requestDTO.description())) {
            task.setDescription(requestDTO.description());
        }
        taskRepository.save(task);
        return new GetTaskByIdResponseDTO(task);
    }


}

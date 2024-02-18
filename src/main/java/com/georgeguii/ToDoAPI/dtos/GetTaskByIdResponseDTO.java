package com.georgeguii.ToDoAPI.dtos;

import com.georgeguii.ToDoAPI.entities.Task;
import com.georgeguii.ToDoAPI.enums.EStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetTaskByIdResponseDTO(UUID id, String title,String description, EStatus status, LocalDateTime createdAt) {
    public GetTaskByIdResponseDTO(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getCreatedAt());
    }
}

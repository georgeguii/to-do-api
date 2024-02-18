package com.georgeguii.ToDoAPI.dtos;

import com.georgeguii.ToDoAPI.entities.Task;
import com.georgeguii.ToDoAPI.enums.EStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetAllTaskResponseDTO(UUID id, String title, EStatus status, LocalDateTime createdAt) {
    public GetAllTaskResponseDTO(Task task) {
        this(task.getId(), task.getTitle(), task.getStatus(), task.getCreatedAt());
    }
}

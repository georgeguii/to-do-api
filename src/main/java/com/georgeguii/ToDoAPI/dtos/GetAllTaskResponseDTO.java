package com.georgeguii.ToDoAPI.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetAllTaskResponseDTO(UUID id, String title, String status, LocalDateTime createdAt) {
}

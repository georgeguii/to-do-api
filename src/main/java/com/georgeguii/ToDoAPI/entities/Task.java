package com.georgeguii.ToDoAPI.entities;

import com.georgeguii.ToDoAPI.dtos.TaskRequestDTO;
import com.georgeguii.ToDoAPI.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 256, nullable = false)
    private String description;

    private EStatus status;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Task(TaskRequestDTO requestDTO) {
        this.title = requestDTO.title();
        this.description = requestDTO.description();
        this.status = EStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    };

}

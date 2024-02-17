package com.georgeguii.ToDoAPI.entities;

import com.georgeguii.ToDoAPI.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 50)
    private String title;

    @Column(length = 256)
    private String description;

    private EStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

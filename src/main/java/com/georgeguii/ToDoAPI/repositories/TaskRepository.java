package com.georgeguii.ToDoAPI.repositories;

import com.georgeguii.ToDoAPI.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}

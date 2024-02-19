package com.georgeguii.ToDoAPI.services;

import com.georgeguii.ToDoAPI.entities.Task;
import com.georgeguii.ToDoAPI.enums.EStatus;
import com.georgeguii.ToDoAPI.repositories.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.georgeguii.ToDoAPI.utils.MockTasks.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    @Autowired
    TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a Task List on getAll")
    void getAllSuccess() {
        when(this.taskRepository.findAll()).thenReturn(mockTasksList());
        var result = this.taskService.getAll();

        assertNotNull(result);
        assertEquals(result.getFirst().title(), TITLE);
    }

    @Test
    @DisplayName("Should return a Empty Task List on getAll")
    void getAllEmpty() {
        when(this.taskRepository.findAll()).thenReturn(new ArrayList<>());
        var result = this.taskService.getAll();

        assertNotNull(result);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should return a Task on getById ")
    void getByIdSuccess() {
        Task task = mockTask();
        when(this.taskRepository.findById(TASK_ID)).thenReturn(Optional.of(task));

        var result = this.taskService.getById(TASK_ID);

        assertNotNull(result);
        assertEquals(result.title(), TITLE);
        assertEquals(result.description(), DESCRIPTION);
        assertEquals(result.status(), EStatus.PENDING);
    }

    @Test
    @DisplayName("Should return null on getById ")
    void getByIdError() {
        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.empty());

        var result = this.taskService.getById(TASK_ID);

        assertNull(result);
    }

    @Test
    void create() {

    }

    @Test
    void update() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    @DisplayName("Should delete Task when exist")
    void deleteSuccess() {
        Task task = mockTask();
        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(task));

        var result = taskService.delete(TASK_ID);
        assertTrue(result);
        Mockito.verify(taskRepository, times(1)).delete(task);
    }

    @Test
    @DisplayName("Should return false when Task not exist")
    void deleteError() {
        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.empty());

        var result = taskService.delete(TASK_ID);
        assertFalse(result);
    }
}
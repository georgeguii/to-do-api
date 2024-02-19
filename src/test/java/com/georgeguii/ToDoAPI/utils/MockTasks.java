package com.georgeguii.ToDoAPI.utils;

import com.georgeguii.ToDoAPI.dtos.TaskRequestDTO;
import com.georgeguii.ToDoAPI.entities.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockTasks {

    public static UUID TASK_ID = UUID.randomUUID();

    public static String TITLE = "Tarefa X";

    public static String DESCRIPTION = "Meu primeiro ToDoList em Java";

    public static TaskRequestDTO mockTaskDTO() {
        return new TaskRequestDTO(TITLE, DESCRIPTION);
    }

    public static Task mockTask() {
        return new Task(mockTaskDTO());
    }

    public static List<Task> mockTasksList() {
        Task task = mockTask();
        List<Task> listTasks = new ArrayList<>();
        listTasks.add(task);
        return listTasks;
    }
}

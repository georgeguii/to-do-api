package com.georgeguii.ToDoAPI.controllers;

import com.georgeguii.ToDoAPI.dtos.GetAllTaskResponseDTO;
import com.georgeguii.ToDoAPI.dtos.TaskRequestDTO;
import com.georgeguii.ToDoAPI.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllTaskResponseDTO> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable UUID id){
        var result = taskService.getById(id);
        if (result == null) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Nenhuma tarefa encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TaskRequestDTO requestDTO) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", taskService.create(requestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody TaskRequestDTO requestDTO) {
        var result = taskService.update(id, requestDTO);

        if (result == null) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Nenhuma tarefa com o id informado foi encontrada.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity updateStatus(@PathVariable UUID id) {
        var result = taskService.updateStatus(id);
        if (result == null) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Nenhuma tarefa com o id informado foi encontrada.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        var isDeleted = taskService.delete(id);
        if (!isDeleted) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Nenhuma tarefa com o id informado foi encontrada.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }
        return ResponseEntity.noContent().build();
    }
}

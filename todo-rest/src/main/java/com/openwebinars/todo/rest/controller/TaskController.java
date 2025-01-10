package com.openwebinars.todo.rest.controller;

import com.openwebinars.todo.rest.dto.EditTaskDto;
import com.openwebinars.todo.rest.dto.GetTaskDto;
import com.openwebinars.todo.rest.model.Task;
import com.openwebinars.todo.rest.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping
    public List<GetTaskDto> getAll() {
        return taskService.findAll()
                .stream()
                .map(GetTaskDto::of)
                .toList();
    }

    @GetMapping("/{id}")
    public GetTaskDto getById(@PathVariable Long id) {
        return GetTaskDto.of(taskService.findById(id));

    }

    @PostMapping
    public ResponseEntity<GetTaskDto> create(@RequestBody EditTaskDto cmd) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                GetTaskDto.of(taskService.save(cmd))
        );
    }

    @PutMapping("/{id}")
    public GetTaskDto edit(@RequestBody EditTaskDto cmd, @PathVariable Long id) {
        return GetTaskDto.of(taskService.edit(cmd, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }



}

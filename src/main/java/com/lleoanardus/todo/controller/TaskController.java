package com.lleoanardus.todo.controller;

import com.lleoanardus.todo.dto.TaskGetDTO;
import com.lleoanardus.todo.dto.TaskInsertDTO;
import com.lleoanardus.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    final TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskGetDTO>> findAll(){return ResponseEntity.ok(service.findAll());}

    @GetMapping("/status")
    public ResponseEntity<List<TaskGetDTO>> findByStatus(
            @RequestParam(defaultValue = "true", name = "completed") Boolean status){
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGetDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskGetDTO> create(@RequestBody TaskInsertDTO taskInsertDTO){
        TaskGetDTO task = service.create(taskInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGetDTO> update(@PathVariable String id, @RequestBody TaskInsertDTO taskInsertDTO){
        return ResponseEntity.ok(service.update(id, taskInsertDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskGetDTO> deleteById(@PathVariable String id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

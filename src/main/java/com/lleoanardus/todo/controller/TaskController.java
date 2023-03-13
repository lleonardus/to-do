package com.lleoanardus.todo.controller;

import com.lleoanardus.todo.controller.exception.StandardError;
import com.lleoanardus.todo.dto.TaskGetDTO;
import com.lleoanardus.todo.dto.TaskInsertDTO;
import com.lleoanardus.todo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks")
public class TaskController {
    final TaskService service;

    @GetMapping
    @Operation(summary = "Get all tasks")
    public ResponseEntity<List<TaskGetDTO>> findAll(){return ResponseEntity.ok(service.findAll());}

    @GetMapping("/status")
    @Operation(summary = "Get tasks based on status",
    responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = TaskGetDTO.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<List<TaskGetDTO>> findByStatus(
            @RequestParam(defaultValue = "true", name = "completed") Boolean status){
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = TaskGetDTO.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content(schema = @Schema(implementation = StandardError.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    public ResponseEntity<TaskGetDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create task",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = TaskGetDTO.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    public ResponseEntity<TaskGetDTO> create(@RequestBody TaskInsertDTO taskInsertDTO){
        TaskGetDTO task = service.create(taskInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(task);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = TaskGetDTO.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content(schema = @Schema(implementation = StandardError.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    public ResponseEntity<TaskGetDTO> update(@PathVariable String id, @RequestBody TaskInsertDTO taskInsertDTO){
        return ResponseEntity.ok(service.update(id, taskInsertDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content(schema = @Schema(implementation = StandardError.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    public ResponseEntity<TaskGetDTO> deleteById(@PathVariable String id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

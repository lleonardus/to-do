package com.lleoanardus.todo.dto;

import com.lleoanardus.todo.model.Task;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskGetDTO {
    private String id;
    private String todo;
    private Boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TaskGetDTO valueOf(Task task){
        return TaskGetDTO.builder()
                .id(task.getId())
                .todo(task.getTodo())
                .isCompleted(task.getIsCompleted())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}

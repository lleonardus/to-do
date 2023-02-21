package com.lleoanardus.todo.dto;

import com.lleoanardus.todo.model.Task;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskInsertDTO {
    private String todo;
    private Boolean isCompleted;

    public Task toTask() {
        return Task.builder()
                .todo(todo)
                .isCompleted(isCompleted)
                .build();
    }
}

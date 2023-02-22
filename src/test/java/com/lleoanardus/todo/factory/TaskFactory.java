package com.lleoanardus.todo.factory;

import com.lleoanardus.todo.dto.TaskGetDTO;
import com.lleoanardus.todo.dto.TaskInsertDTO;
import com.lleoanardus.todo.model.Task;

public class TaskFactory {

    public static final String TODO = "todo";
    public static final boolean IS_COMPLETED = true;
    public static final String ID = "id";

    public static Task createTask(){
        return Task.builder()
                .id(ID)
                .todo(TODO)
                .isCompleted(IS_COMPLETED)
                .build();
    }

    public static TaskGetDTO createTaskGetDTO(){
        return TaskGetDTO.builder()
                .id(ID)
                .todo(TODO)
                .isCompleted(IS_COMPLETED)
                .build();
    }

    public static TaskInsertDTO createTaskInsertDTO(){
        return TaskInsertDTO.builder()
                .todo(TODO)
                .isCompleted(IS_COMPLETED)
                .build();
    }
}

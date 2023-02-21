package com.lleoanardus.todo.service.exception;

public class TaskNotFound extends RuntimeException{
    public TaskNotFound() {
        super("Task not found");
    }
}

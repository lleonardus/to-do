package com.lleoanardus.todo.service.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException() {
        super("Task not found");
    }
}

package com.lleoanardus.todo.controller.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StandardError {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;
}

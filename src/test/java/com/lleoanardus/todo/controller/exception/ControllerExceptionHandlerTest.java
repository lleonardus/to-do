package com.lleoanardus.todo.controller.exception;

import com.lleoanardus.todo.service.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class ControllerExceptionHandlerTest {
    @InjectMocks
    ControllerExceptionHandler handler;

    @Test
    void taskNotFound() {
        ResponseEntity<StandardError> response = handler.taskNotFound(new TaskNotFoundException(),
                new MockHttpServletRequest());

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getTimestamp());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getMessage());
        assertNotNull(response.getBody().getPath());
    }
}
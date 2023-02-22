package com.lleoanardus.todo.controller;

import com.lleoanardus.todo.dto.TaskGetDTO;
import com.lleoanardus.todo.dto.TaskInsertDTO;
import com.lleoanardus.todo.factory.TaskFactory;
import com.lleoanardus.todo.service.TaskService;
import com.lleoanardus.todo.service.exception.TaskNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TaskControllerTest {

    public static final String NON_EXISTING_ID = TaskFactory.ID + "s";
    @InjectMocks
    TaskController controller;

    @Mock
    TaskService service;

    TaskGetDTO taskGetDTO;
    TaskInsertDTO taskInsertDTO;

    @BeforeEach
    void setUp() {
        taskGetDTO = TaskFactory.createTaskGetDTO();
        taskInsertDTO = TaskFactory.createTaskInsertDTO();

        when(service.findAll()).thenReturn(List.of(taskGetDTO));
        when(service.findByStatus(TaskFactory.IS_COMPLETED)).thenReturn(List.of(taskGetDTO));
        when(service.findById(TaskFactory.ID)).thenReturn(taskGetDTO);
        when(service.findById(NON_EXISTING_ID)).thenThrow(TaskNotFoundException.class);
        when(service.create(taskInsertDTO)).thenReturn(taskGetDTO);
        when(service.update(TaskFactory.ID, taskInsertDTO)).thenReturn(taskGetDTO);
        when(service.update(NON_EXISTING_ID, taskInsertDTO)).thenThrow(TaskNotFoundException.class);
        doNothing().when(service).deleteById(TaskFactory.ID);
        doThrow(TaskNotFoundException.class).when(service).deleteById(NON_EXISTING_ID);
    }

    @Test
    @DisplayName("findAll returns a list of tasks")
    void findAll_ReturnsAListOfTasks(){
        ResponseEntity<List<TaskGetDTO>> response = controller.findAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(List.of(taskGetDTO), response.getBody());
    }

    @Test
    @DisplayName("findByStatus returns a list of tasks")
    void findByStatus_ReturnsAListOfTasks(){
        ResponseEntity<List<TaskGetDTO>> response = controller.findByStatus(TaskFactory.IS_COMPLETED);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(List.of(taskGetDTO), response.getBody());
    }

    @Test
    @DisplayName("findById, when task exists, returns a task")
    void findById_WhenTaskExists_ReturnsATask(){
        ResponseEntity<TaskGetDTO> response = controller.findById(TaskFactory.ID);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(taskGetDTO, response.getBody());
    }

    @Test
    @DisplayName("findById, when task does not exist, throws TaskNotFoundException")
    void findById_WhenTaskDoesNotExist_ThrowsTaskNotFoundException(){
        assertThrows(TaskNotFoundException.class, () -> controller.findById(NON_EXISTING_ID));
    }

    @Test
    @DisplayName("create, when successful, returns a task")
    void create_WhenSuccessful_ReturnsATask(){
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);

        ResponseEntity<TaskGetDTO> response = controller.create(taskInsertDTO);

        assertNotNull(response);
        assertNotNull(response.getHeaders().getLocation());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(taskGetDTO, response.getBody());
    }

    @Test
    @DisplayName("update, when task exists, returns a task")
    void update_WhenTaskExists_ReturnsATask(){
        ResponseEntity<TaskGetDTO> response = controller.update(TaskFactory.ID, taskInsertDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(taskGetDTO, response.getBody());
    }

    @Test
    @DisplayName("update, when task does not exist, throws TaskNotFoundException")
    void update_WhenTaskDoesNotExist_ThrowsTaskNotFoundException(){
        assertThrows(TaskNotFoundException.class, () -> controller.update(NON_EXISTING_ID, taskInsertDTO));
    }

    @Test
    @DisplayName("deleteById, when task exists, does nothing")
    void deleteById_WhenTaskExists_DoesNothing(){
        ResponseEntity<TaskGetDTO> response = controller.deleteById(TaskFactory.ID);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("deleteById, when task does not exist, throws TaskNotFoundException")
    void deleteById_WhenTaskDoesNotExist_ThrowsTaskNotFoundException(){
        assertThrows(TaskNotFoundException.class, () -> controller.deleteById(NON_EXISTING_ID));
    }
}
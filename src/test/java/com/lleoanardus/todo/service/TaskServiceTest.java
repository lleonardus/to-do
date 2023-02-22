package com.lleoanardus.todo.service;

import com.lleoanardus.todo.dto.TaskGetDTO;
import com.lleoanardus.todo.dto.TaskInsertDTO;
import com.lleoanardus.todo.factory.TaskFactory;
import com.lleoanardus.todo.model.Task;
import com.lleoanardus.todo.repository.TaskRepository;
import com.lleoanardus.todo.service.exception.TaskNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @InjectMocks
    TaskService service;

    @Mock
    TaskRepository repository;

    Task task;
    TaskGetDTO taskGetDTO;
    TaskInsertDTO taskInsertDTO;

    @BeforeEach
    void setUp() {
        task = TaskFactory.createTask();
        taskGetDTO = TaskFactory.createTaskGetDTO();
        taskInsertDTO = TaskFactory.createTaskInsertDTO();

        when(repository.findAll()).thenReturn(List.of(task));
        when(repository.findById(TaskFactory.ID)).thenReturn(Optional.of(task));
        when(repository.findByIsCompleted(TaskFactory.IS_COMPLETED)).thenReturn(Optional.of(task));
        when(repository.save(taskInsertDTO.toTask())).thenReturn(task);
        doNothing().when(repository).deleteById(TaskFactory.ID);
    }

    @Test
    @DisplayName("findAll returns a list of tasks")
    void findAll_ReturnsAListOfTasks() {
        List<TaskGetDTO> response = service.findAll();

        assertNotNull(response);
        assertEquals(List.of(taskGetDTO), response);
    }

    @Test
    @DisplayName("findByStatus returns a list of tasks")
    void findByStatus_ReturnsAListOfTasks() {
        List<TaskGetDTO> response = service.findByStatus(TaskFactory.IS_COMPLETED);

        assertNotNull(response);
        assertEquals(List.of(taskGetDTO), response);
    }

    @Test
    @DisplayName("findById, when task exists, returns a task")
    void findById_WhenTaskExists_ReturnsATask() {
        TaskGetDTO response = service.findById(TaskFactory.ID);

        assertNotNull(response);
        assertEquals(taskGetDTO, response);
    }

    @Test
    @DisplayName("findById, when task does not exist, throws TaskNotFoundException")
    void findById_WhenTaskDoesNotExist_ThrowsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> service.findById(TaskFactory.ID + "s"));
    }

    @Test
    @DisplayName("create, when successful, returns a task")
    void create_WhenSuccessful_ReturnsATask() {
        TaskGetDTO response = service.create(taskInsertDTO);

        assertNotNull(response);
        assertEquals(taskGetDTO, response);
    }

    @Test
    @DisplayName("update, when task exists, returns a task")
    void update_WhenTaskExists_ReturnsATask() {
        when(repository.findById(TaskFactory.ID)).thenReturn(Optional.of(taskInsertDTO.toTask()));
        TaskGetDTO response = service.update(TaskFactory.ID, taskInsertDTO);

        assertNotNull(response);
        assertEquals(taskGetDTO, response);
    }

    @Test
    @DisplayName("update, when task does not exist, throws TaskNotFoundException")
    void update_WhenTaskDoesNotExist_ThrowsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> service.update(TaskFactory.ID + "s", taskInsertDTO));
    }

    @Test
    @DisplayName("deleteById, when task exists, does nothing")
    void deleteById_WhenTaskExists_DoesNothing() {
        service.deleteById(TaskFactory.ID);

        verify(repository, times(1)).delete(task);
    }

    @Test
    @DisplayName("deleteById, when task does not exist, throws TaskNotFoundException")
    void deleteById_WhenTaskDoesNotExist_ThrowsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> service.deleteById(TaskFactory.ID + "s"));
    }
}
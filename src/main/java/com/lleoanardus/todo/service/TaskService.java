package com.lleoanardus.todo.service;

import com.lleoanardus.todo.dto.TaskGetDTO;
import com.lleoanardus.todo.dto.TaskInsertDTO;
import com.lleoanardus.todo.model.Task;
import com.lleoanardus.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    final TaskRepository repository;

    public List<TaskGetDTO> findAll(){
        return repository.findAll().stream().map(TaskGetDTO::valueOf).toList();
    }

    public List<TaskGetDTO> findByStatus(Boolean status){
        return repository.findByIsCompleted(status).stream().map(TaskGetDTO::valueOf).toList();
    }

    public TaskGetDTO findById(String id){
        Task task = this.findByIdOrElseThrowTaskNotFoundException(id);
        return TaskGetDTO.valueOf(task);
    }

    public TaskGetDTO create(TaskInsertDTO taskInsertDTO){
        Task task = repository.save(taskInsertDTO.toTask());

        return TaskGetDTO.valueOf(task);
    }

    public TaskGetDTO update(String id, TaskInsertDTO taskInsertDTO){
        Task task = this.findByIdOrElseThrowTaskNotFoundException(id);

        task.setTodo(taskInsertDTO.getTodo());
        task.setIsCompleted(taskInsertDTO.getIsCompleted());

        return TaskGetDTO.valueOf(repository.save(task));
    }

    public void deleteById(String id){
        repository.delete(this.findByIdOrElseThrowTaskNotFoundException(id));
    }

    private Task findByIdOrElseThrowTaskNotFoundException(String id) {
        return repository.findById(id).orElseThrow();
    }
}

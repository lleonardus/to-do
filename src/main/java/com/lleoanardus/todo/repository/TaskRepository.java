package com.lleoanardus.todo.repository;

import com.lleoanardus.todo.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    Optional<Task> findByIsCompleted(Boolean isCompleted);
}

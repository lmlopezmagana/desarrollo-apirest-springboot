package com.openwebinars.todo.rest.repos;

import com.openwebinars.todo.rest.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

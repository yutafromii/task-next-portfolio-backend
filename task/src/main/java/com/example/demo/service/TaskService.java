package com.example.demo.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  public Task findById(Long id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
  }

  @Transactional
  public Task createTask(Task input) {
    Task t = new Task();
    t.setName(input.getName());
    t.setCompleted(input.getCompleted() != null ? input.getCompleted() : Boolean.FALSE);
    return taskRepository.save(t);
  }

  @Transactional
  public Task updateTask(Long id, Task update) {
    Task current = taskRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
    current.setName(update.getName());
    current.setCompleted(update.getCompleted());
    return taskRepository.save(current);
  }

  @Transactional
  public void deleteTask(Long id) {
    Task entity = taskRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
    taskRepository.delete(entity);
  }
}

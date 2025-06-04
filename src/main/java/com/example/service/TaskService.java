package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.model.Project;
import com.example.model.Task;
import com.example.repository.ProjectRepository;
import com.example.repository.TaskRepository;

@Service
public class TaskService {

	
private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository=taskRepository;
	}
	
	public List<Task> getAllTask(){
		return taskRepository.findAll();
	}
	
	public Optional<Task> getTaskById(Long id) {
		return taskRepository.findById(id);
	}
	
	public void saveTask(Task task) {
		taskRepository.save(task);
	
	}
	
	
	public void deleteTaskById(Long id) {
		taskRepository.deleteById(id);
	}
	
	
	
}

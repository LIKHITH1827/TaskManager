package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.Project;
import com.example.api.model.Task;
import com.example.api.repository.ProjectRepository;
import com.example.api.repository.TaskRepository;

@Service
public class TaskService {

	
private final TaskRepository taskRepository;
	
   
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository=taskRepository;
	}
	
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	public Optional<Task> getTaskById(Long id) {
		return taskRepository.findById(id);
	}
	
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	
	}
	
	
	public void deleteTaskById(Long id) {
		taskRepository.deleteById(id);
	}
	
	
	
}

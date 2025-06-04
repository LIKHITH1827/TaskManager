package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Project;
import com.example.repository.ProjectRepository;

@Service
public class ProjectService {

	
	private final ProjectRepository projectRepository;
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository=projectRepository;
	}
	
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	public Optional<Project> getProjectById(Long id) {
		return projectRepository.findById(id);
	}
	
	public void saveProject(Project project) {
	   projectRepository.save(project);
	
	}
	
	
	public void deleteProjectById(Long id) {
		projectRepository.deleteById(id);
	}
	
	
	
	
	
	
}
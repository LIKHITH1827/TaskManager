package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.Project;
import com.example.api.repository.ProjectRepository;

@Service
public class ProjectService {

	
	private final ProjectRepository projectRepository;
	
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository=projectRepository;
	}
	
	public  List<Project>  getAllProjects(){
		return projectRepository.findAll();
	}
	
	public Optional<Project> getProjectById(Long id) {
		return projectRepository.findById(id);
	}
	
	public Project saveProject(Project project) {
	   return projectRepository.save(project);
	
	}
	
	
	public void deleteProject(Long id) {
		projectRepository.deleteById(id);
	}
	
	
	
	
	
	
}
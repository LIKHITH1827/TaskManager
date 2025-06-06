package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Project;
import com.example.model.Task;
import com.example.service.ProjectService;


@CrossOrigin(origins ="https://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	private final ProjectService projectService;
	
	public ProjectController(ProjectService projectService) {
		this.projectService=projectService;
	}
	
	@GetMapping("/")
	public List<Project> getAllProjects(){
		return projectService.getAllProjects();
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
		Optional<Project> project=projectService.getProjectById(id);
		if(project.isPresent()) {
			return ResponseEntity.ok(project.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping()
	public void CreateProject(@RequestBody Project project) {
		projectService.saveProject(project);
	}
	
	
	@PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            Project updatedProject = project.get();
            updatedProject.setName(projectDetails.getName());
            updatedProject.setDescription(projectDetails.getDescription());

            // Clear the current tasks to ensure fresh association
            updatedProject.getTasks().clear();

            // Re-add tasks, ensuring that each task is associated with the project
            for (Task task : projectDetails.getTasks()) {
                updatedProject.addTask(task);
            }
            return ResponseEntity.ok(projectService.saveProject(updatedProject));}
            		
            		
            else {
            	return ResponseEntity.notFound().build();
            }
            
        
    }

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
	   projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
   
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
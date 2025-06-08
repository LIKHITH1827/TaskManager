package com.example.api.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Project;
import com.example.api.model.Task;
import com.example.api.service.ProjectService;


@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")
@RestController
public class ProjectController {

	private static final Logger logger= LoggerFactory.getLogger(ProjectController.class);
	private final ProjectService projectService;
	
	public ProjectController(ProjectService projectService) {
		this.projectService=projectService;
	}
	
	@GetMapping("/api/projects")
	public List<Project> getAllProjects(){
		logger.info("GET api/projects");
		return projectService.getAllProjects();
		
	}
	
	@GetMapping("/api/projects/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) {
		Optional<Project> project=projectService.getProjectById(id);
		if(project.isPresent()) {
			return ResponseEntity.ok(project.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/api/projects")
	public Project CreateProject(@RequestBody Project project) {
		return projectService.saveProject(project);
	}
	
	
	@PutMapping("/api/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, @RequestBody Project projectDetails) {
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

	
	
	@DeleteMapping("/api/projects/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
	   projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
   
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
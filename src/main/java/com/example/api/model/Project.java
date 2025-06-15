package com.example.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Project {
	

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   private String name;
   private String description;
   
   @OneToMany(mappedBy="project",cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Task> tasks;
   
  public void addTask(Task task) {
	  tasks.add(task);
	 task.setProject(this);
  }
  public void removeTask(Task task) {
	  tasks.remove(task);
	task.setProject(null);
  }
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public List<Task> getTasks() {
	return tasks;
}
public void setTasks(List<Task> tasks) {
	this.tasks = tasks;
}

}

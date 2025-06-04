package com.example.model;

import java.util.List;

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
   
  private void AddTask(Task task) {
	  tasks.add(task);
	 // task.setProject(this);
  }
  private void RemoveTask(Task task) {
	  tasks.remove(task);
	  //tasks.setProject(null);
  }

}

package com.example.api.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.web.ProjectedPayload;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
	private String name;
	private String description;
	@Column(nullable = false)
	private Boolean completed=false;
	private LocalDate dueDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@JsonBackReference
	private Project project;
	
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
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	

	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
}

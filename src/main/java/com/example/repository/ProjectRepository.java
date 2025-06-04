package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}

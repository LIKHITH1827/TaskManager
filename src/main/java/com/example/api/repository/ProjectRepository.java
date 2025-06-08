package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}

package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{

}

package com.example.catlearn.catlearn.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.catlearn.catlearn.model.Course;


@CrossOrigin("http://localhost:4200")
public interface ModuleRepository extends JpaRepository<com.example.catlearn.catlearn.model.Module, Long> {

	
	Optional<com.example.catlearn.catlearn.model.Module> findByIdAndCourse_id(long idModule,long IdCourse);
	
	List<com.example.catlearn.catlearn.model.Module> findByCourse_id(long idCourse);
	
}

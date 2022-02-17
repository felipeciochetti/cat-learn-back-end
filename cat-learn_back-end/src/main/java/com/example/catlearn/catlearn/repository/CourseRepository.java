package com.example.catlearn.catlearn.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.catlearn.catlearn.model.Course;


@CrossOrigin("http://localhost:4200")
public interface CourseRepository extends JpaRepository<Course, Long> {


    List<Course> findByName(String name);
	
}

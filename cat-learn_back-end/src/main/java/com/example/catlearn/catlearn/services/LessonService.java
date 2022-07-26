package com.example.catlearn.catlearn.services;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catlearn.catlearn.model.Module;
import com.example.catlearn.catlearn.exception.ResourceNotFoundException;
import com.example.catlearn.catlearn.model.Course;
import com.example.catlearn.catlearn.model.Lesson;
import com.example.catlearn.catlearn.repository.ModuleRepository;
import com.example.catlearn.catlearn.repository.LessonRepository;
import com.example.catlearn.catlearn.utils.ContextUtils;
import com.example.catlearn.catlearn.utils.Functions;



@Service
public class LessonService   implements Serializable{


	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LessonService.class);
	
	

	@Autowired
	LessonRepository repository;

	@Autowired
	ModuleRepository repositoryModule;


	int first, size;

	public LessonService() {
	}





	public Lesson adicionar(Course course,Module module, Lesson lesson) {
		// TODO Auto-generated method stub
		
		
		lesson.setModule(module);

    	Lesson new_lesson = repository.save(lesson);
    	
		
		return new_lesson;
	}



}

package com.example.catlearn.catlearn.services;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catlearn.catlearn.model.Course;
import com.example.catlearn.catlearn.model.Module;
import com.example.catlearn.catlearn.repository.CourseRepository;
import com.example.catlearn.catlearn.repository.ModuleRepository;
import com.example.catlearn.catlearn.utils.ContextUtils;
import com.example.catlearn.catlearn.utils.Functions;



@Service
public class ModuleService   implements Serializable{


	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ModuleService.class);
	
	

	@Autowired
	ModuleRepository repository;

	@Autowired
	CourseRepository repositoryCourse;


	int first, size;

	public ModuleService() {
	}



	public Module adicionar(com.example.catlearn.catlearn.model.Module module) throws Exception{


		
		return repository.save(module);



	}



	public Module adicionar(Course course, Module module) {
		// TODO Auto-generated method stub
		
		
		
		
		module.setCourse(course);

    	com.example.catlearn.catlearn.model.Module new_module = repository.save(module);
    	
		
		return new_module;
	}



}

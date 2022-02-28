package com.example.catlearn.catlearn.services;

import java.io.File;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.catlearn.catlearn.exception.ResourceNotFoundException;
import com.example.catlearn.catlearn.model.Course;
import com.example.catlearn.catlearn.model.Lesson;
import com.example.catlearn.catlearn.repository.CourseRepository;
import com.example.catlearn.catlearn.utils.ContextUtils;
import com.example.catlearn.catlearn.utils.Functions;



@Service
public class CustomerService   implements Serializable{


	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	

	@Autowired
	CourseRepository repository;



	int first, size;

	public CustomerService() {
	}



	public Course adicionar(Course course) {

		String msg = validaDadosCourse(course);

		if(!"".equals(msg)){
			throw new ResourceNotFoundException(msg);
		}
		setPathImagemCapa(course);

		
		
		/*
		setCourseToModule(course ,course.getModules());
		setModuleToLessons(course.getModules());
		
		*/


		
		return repository.save(course);



	}

	private void setPathImagemCapa(Course course) {

		String path = ContextUtils.getInstance().getPathFilesAttachedsCatLearnCourse() + course.getCode() + File.separator  + "imagem_capa_course" + ".jpg";

		course.setImageUrl(path);

	}


	private void setModuleToLessons(List<Module> modules) {

		
	
	}


	private void setCourseToModule(Course course,List<Module> list) {

		if(list == null){
			return;
		}

		for (Module modulos : list) {
			//modulos.setCourse(course);
		}
	}


	public String validaDadosCourse(Course course){
		String msg = "";

		if("".equals(course.getCode()) ||  course.getCode() == null){
			course.setCode(Functions.gerarNumeroAleatorio(5));
		}

		return "";
	}


	public void update(Course course) throws Exception {
		
	/*
		Course old = repositorio.get(course.getId());
		course.setModules(old.getModules());
		String msg = validaDadosCourse(course);

		if(!"".equals(msg)){
			throw new Exception(msg);
		}
		
		repositorio.atualizar(course);
		
		
		*/
		
	}


}

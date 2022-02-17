package com.example.catlearn.catlearn.api;


import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.catlearn.catlearn.exception.ResourceNotFoundException;
import com.example.catlearn.catlearn.model.Course;
import com.example.catlearn.catlearn.repository.CourseRepository;
import com.example.catlearn.catlearn.services.CourseService;
import com.example.catlearn.catlearn.utils.ContextUtils;
import com.example.catlearn.catlearn.utils.Functions;
import com.example.catlearn.catlearn.utils.UploadFileUtils;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CourseResource  implements Serializable{



	@Autowired
	CourseRepository repository;

	
	@Autowired
	CourseService service;



	@GetMapping("/courses")
	
	public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String title) {
	
			List<Course> courses = new ArrayList<Course>();

			if (title == null)
				repository.findAll().forEach(courses::add);
			else {
				repository.findByName(title).forEach(courses::add);

			}
			
			
			 return new  ResponseEntity<>(courses, HttpStatus.OK);
	}

	@GetMapping("/courses/{id}")
	  public ResponseEntity<Course> getCourseById(@PathVariable("id") long id) {
	  
		
		Course data = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course: " + id));



	      return new ResponseEntity<>(data, HttpStatus.OK);
	  
	  }


	@PostMapping("/courses")
	  public ResponseEntity<Course> createCourse(@RequestBody Course course) {
	    	
	    	Course new_course = service.adicionar(course);
	    	
	    	
	      return new ResponseEntity<>(new_course, HttpStatus.CREATED);
	  
	  }
	

	 @PutMapping("/courses/{id}")
	  public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course tutorial) {
	   // Optional<Course> tutorialData = repository.findById(id);
		 

			Course data = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course: " + id));
			
			tutorial.setId(data.getId());
		tutorial.setModules(data.getModules());

	      return new ResponseEntity<>(repository.save(tutorial), HttpStatus.OK);
	    
	  }
	 
	 

	 @DeleteMapping("/courses/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	      repository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }


		
		@PostMapping("/courses/course-img/{code}")

		public ResponseEntity uploadImage(
				@PathVariable("code") String code,
				InputStream fileInputStream){
				
			

			System.out.println(fileInputStream.toString());


			Functions.criarDiretorio(ContextUtils.getInstance().getPathFilesAttachedsCatLearnCourse()  +  code);

			String newFileName = ContextUtils.getInstance().getPathFilesAttachedsCatLearnCourse() + code + File.separator +   "imagem_capa_course" + ".jpg";

			// save it
			
			UploadFileUtils.writeToFile(fileInputStream, newFileName);


			   String output = "File uploaded to : " + newFileName;
				   System.out.println(output);
				   
			return new ResponseEntity<>(HttpStatus.OK);
		}







}
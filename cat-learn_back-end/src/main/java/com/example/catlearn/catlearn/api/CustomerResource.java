package com.example.catlearn.catlearn.api;


import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.catlearn.catlearn.exception.ResourceNotFoundException;
import com.example.catlearn.catlearn.model.Course;
import com.example.catlearn.catlearn.repository.CourseRepository;
import com.example.catlearn.catlearn.repository.ModuleRepository;
import com.example.catlearn.catlearn.services.ModuleService;
import com.example.catlearn.catlearn.utils.ContextUtils;
import com.example.catlearn.catlearn.utils.Functions;
import com.example.catlearn.catlearn.utils.UploadFileUtils;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerResource  implements Serializable{



	@Autowired
	ModuleRepository repository;


	@Autowired
	CourseRepository repositoryCourse;


	@Autowired
	ModuleService service;


	@GetMapping("/courses/{idCourse}/modules")
	public ResponseEntity<List<com.example.catlearn.catlearn.model.Module>> getAllModulesByCourse(@PathVariable("idCourse") long idCourse) {

		List<com.example.catlearn.catlearn.model.Module> modules = repository.findByCourse_id(idCourse);


		return new  ResponseEntity<>(modules, HttpStatus.OK);
	}


	@GetMapping("/courses/{idCourse}/module/{idModule}")
	public ResponseEntity<com.example.catlearn.catlearn.model.Module> getModuleById(@PathVariable("idCourse") long idCourse,@PathVariable("idModule") long idModule) {

		com.example.catlearn.catlearn.model.Module data = repository.findByIdAndCourse_id(idModule,idCourse).orElseThrow(() -> new ResourceNotFoundException("Modulo: " + idModule));

		return new ResponseEntity<>(data, HttpStatus.OK);
	}


	@PostMapping("/courses/{idCourse}/module")
	public ResponseEntity<com.example.catlearn.catlearn.model.Module> createModule(@PathVariable("idCourse") long idCourse,@RequestBody com.example.catlearn.catlearn.model.Module module) {
		
		Course course = repositoryCourse.findById(idCourse).orElseThrow(
				() -> new ResourceNotFoundException("Curso:" + idCourse));

		

			com.example.catlearn.catlearn.model.Module new_module = service.adicionar(course,module);


			return new ResponseEntity<>(new_module, HttpStatus.CREATED);
		
	}


	@PutMapping("/courses/{idCourse}/module/{idModule}")
	public ResponseEntity<com.example.catlearn.catlearn.model.Module> updateModule(@PathVariable("idCourse") long idCourse,@PathVariable("idModule") long idModule, @RequestBody com.example.catlearn.catlearn.model.Module module) {

		Course course = repositoryCourse.findById(idCourse).orElseThrow(() -> new ResourceNotFoundException("Curso:" + idCourse));


		
		com.example.catlearn.catlearn.model.Module dataModule = repository.findByIdAndCourse_id(idModule,idCourse).orElseThrow(() -> new ResourceNotFoundException("MOdulo:" + idModule));
	
		module.setCourse(course);
		module.setId(dataModule.getId());
		module.setLessons(dataModule.getLessons());
		repository.save(module);

		return ResponseEntity.noContent().build();



	}


	@DeleteMapping("/courses/{idCourse}/module/{idModule}")
	public ResponseEntity<com.example.catlearn.catlearn.model.Module> delete(@PathVariable Long idCourse,@PathVariable Long idModule) {
		com.example.catlearn.catlearn.model.Module dataModule = repository.findByIdAndCourse_id(idModule,idCourse).orElseThrow(() -> new ResourceNotFoundException("MOdulo:" + idModule));
		
		repository.delete(dataModule);

		return ResponseEntity.noContent().build();
	}



}
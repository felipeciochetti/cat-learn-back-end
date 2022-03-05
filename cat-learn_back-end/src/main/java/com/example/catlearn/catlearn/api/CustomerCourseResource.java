package com.example.catlearn.catlearn.api;


import java.io.Serializable;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.catlearn.catlearn.exception.ResourceNotFoundException;
import com.example.catlearn.catlearn.model.Course;
import com.example.catlearn.catlearn.model.CustomerCourse;
import com.example.catlearn.catlearn.model.CustomerCourseKey;
import com.example.catlearn.catlearn.model.Pessoa;
import com.example.catlearn.catlearn.repository.CourseRepository;
import com.example.catlearn.catlearn.repository.CustomerCourseRepository;
import com.example.catlearn.catlearn.repository.PessoaRepository;
import com.example.catlearn.catlearn.services.ModuleService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerCourseResource  implements Serializable{



	@Autowired
	CustomerCourseRepository repository;


	@Autowired
	CourseRepository repositoryCourse;



	@Autowired
	PessoaRepository repositoryPessoa;

	
	@Autowired
	ModuleService service;


	@GetMapping("/customer/{idCustomer}/course/{idCourse}")
	public ResponseEntity<Object> 
	getCoursesAndCustomerById(@PathVariable("idCustomer") long idCustomer,@PathVariable("idCourse") long idCourse) {

		Object course = repository.findByPessoa_IdPessoaAndCourse_Id(idCustomer, idCourse)
				.orElseThrow(() -> new ResourceNotFoundException("Customer: " +  idCustomer + " , Course: " + idCourse));


		
		
		return new  ResponseEntity<>(course, HttpStatus.OK);
	}


	@GetMapping("/customer/{idCustomer}/courses")
	public ResponseEntity<List<CustomerCourse>> 
	getAllCoursesByCustomer(@PathVariable("idCustomer") long idCustomer) {
		
		
		List<CustomerCourse> list = repository.findByIdPessoaId(idCustomer);


		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	@PostMapping("/customer/{idCustomer}/courses/{idCourse}")
	public ResponseEntity<CustomerCourse> createCustomerCourse(@PathVariable("idCustomer") long idCustomer,@PathVariable("idCourse") long idCourse) {
		
		Course course = repositoryCourse.findById(idCourse).orElseThrow(
				() -> new ResourceNotFoundException("Curso:" + idCourse));

		Pessoa pessoa = repositoryPessoa.findById(idCustomer).orElseThrow(
				() -> new ResourceNotFoundException("Pessoa:" + idCustomer));

			
		CustomerCourseKey pk = new CustomerCourseKey();
			pk.setCourseId(course.getId());
			pk.setPessoaId(pessoa.getIdPessoa());
			CustomerCourse data = new CustomerCourse();
			data.setCourse(course);
			data.setPessoa(pessoa);
			data.setId(pk);
			
			 data = repository.save(data);
		

			return new ResponseEntity<>(data, HttpStatus.CREATED);
		
	}


	@PutMapping("/customer/{idCustomer}/courses/{idCourse}")
	public ResponseEntity<com.example.catlearn.catlearn.model.Module> updateModule(@PathVariable("idCustomer") long idCustomer,@PathVariable("idCourse") long idCourse) {

		Course course = repositoryCourse.findById(idCourse).orElseThrow(() -> new ResourceNotFoundException("Curso:" + idCourse));

		Pessoa pessoa = repositoryPessoa.findById(idCustomer).orElseThrow(
				() -> new ResourceNotFoundException("Pessoa:" + idCustomer));

		
		CustomerCourse dataModule = repository.findByPessoa_IdPessoaAndCourse_Id(idCustomer,idCourse).orElseThrow(() -> new ResourceNotFoundException("Course:" + idCourse + " , Customer: "+ idCustomer));
	
		
		//delete and create a new one below
		repository.delete(dataModule);
		
		CustomerCourseKey pk = new CustomerCourseKey();
		pk.setCourseId(course.getId());
		pk.setPessoaId(pessoa.getIdPessoa());
		CustomerCourse data = new CustomerCourse();
		data.setCourse(course);
		data.setPessoa(pessoa);
		data.setId(pk);
		
		 data = repository.save(data);
	
		return ResponseEntity.noContent().build();



	}


	@DeleteMapping("/customer/{idCustomer}/courses/{idCourse}")
	public ResponseEntity<CustomerCourse> delete(@PathVariable("idCustomer") long idCustomer,@PathVariable("idCourse") long idCourse) {
		
		CustomerCourse dataModule = repository.findByPessoa_IdPessoaAndCourse_Id(idCustomer,idCourse).orElseThrow(() -> new ResourceNotFoundException("Course:" + idCourse + " , Customer: "+ idCustomer));
	
		
		//delete and create a new one below
		repository.delete(dataModule);

		return ResponseEntity.noContent().build();
	}



}
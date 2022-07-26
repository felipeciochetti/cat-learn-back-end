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
import com.example.catlearn.catlearn.model.Module;
import com.example.catlearn.catlearn.repository.CourseRepository;
import com.example.catlearn.catlearn.repository.LessonRepository;
import com.example.catlearn.catlearn.repository.ModuleRepository;
import com.example.catlearn.catlearn.services.LessonService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LessonResource  implements Serializable{



	@Autowired
	LessonRepository repository;


	@Autowired
	ModuleRepository repositoryModule;


	@Autowired
	CourseRepository repositoryCourse;


	@Autowired
	LessonService service;


	@GetMapping("/module/{idModule}/lessons")
	public ResponseEntity<List<com.example.catlearn.catlearn.model.Lesson>> getAllLessonsByModule(@PathVariable("idModule") long idModule) {

		List<com.example.catlearn.catlearn.model.Lesson> lessons = repository.findByModule_id(idModule);


		return new  ResponseEntity<>(lessons, HttpStatus.OK);
	}

	
	@GetMapping("/course/{idCourse}/lessons")
	public ResponseEntity<List<com.example.catlearn.catlearn.model.Lesson>> getAllLessonsByCourse(@PathVariable("idCourse") long idCourse) {

		//List<com.example.catlearn.catlearn.model.Lesson> lessons = repository.findByCourse_id(idCourse);


		return new  ResponseEntity<>(null, HttpStatus.OK);
	}

	
	@GetMapping("/module/{idModule}/lesson/{idLesson}")
	public ResponseEntity<com.example.catlearn.catlearn.model.Lesson> getLessonById(@PathVariable("idModule") long idModule,@PathVariable("idLesson") long idLesson) {

		com.example.catlearn.catlearn.model.Lesson data = repository.findByIdAndModule_id(idLesson,idModule).orElseThrow(() -> new ResourceNotFoundException("Modulo: " + idLesson));

		return new ResponseEntity<>(data, HttpStatus.OK);
	}


	@PostMapping("/module/{idModule}/lesson")
	public ResponseEntity<com.example.catlearn.catlearn.model.Lesson> createLesson(@PathVariable("idModule") long idModule,@RequestBody com.example.catlearn.catlearn.model.Lesson lesson) throws Exception {


		Module module = repositoryModule.findById(idModule).orElseThrow(
				() -> new ResourceNotFoundException("Module:" + idModule));



		Course course  = repositoryCourse.findById(module.getCourse().getId()).orElseThrow(
				() -> new ResourceNotFoundException("Module:" + idModule));




		com.example.catlearn.catlearn.model.Lesson new_lesson = service.adicionar(course,module,lesson);


		return new ResponseEntity<>(new_lesson, HttpStatus.CREATED);

	}


	@PutMapping("/module/{idModule}/lesson/{idLesson}")
	public ResponseEntity<com.example.catlearn.catlearn.model.Lesson> updateLesson(@PathVariable("idModule") long idModule,@PathVariable("idLesson") long idLesson, @RequestBody com.example.catlearn.catlearn.model.Lesson lesson) {

		Module course = repositoryModule.findById(idModule).orElseThrow(() -> new ResourceNotFoundException("Curso:" + idModule));



		com.example.catlearn.catlearn.model.Lesson dataLesson = repository.findByIdAndModule_id(idLesson,idModule).orElseThrow(() -> new ResourceNotFoundException("MOdulo:" + idLesson));

		lesson.setModule(course);
		lesson.setId(dataLesson.getId());
		repository.save(lesson);

		return ResponseEntity.noContent().build();



	}


	@DeleteMapping("/module/{idModule}/lesson/{idLesson}")
	public ResponseEntity<com.example.catlearn.catlearn.model.Lesson> delete(@PathVariable Long idModule,@PathVariable Long idLesson) {
		com.example.catlearn.catlearn.model.Lesson dataLesson = repository.findByIdAndModule_id(idLesson,idModule).orElseThrow(() -> new ResourceNotFoundException("MOdulo:" + idLesson));

		repository.delete(dataLesson);

		return ResponseEntity.noContent().build();
	}



}
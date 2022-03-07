package com.example.catlearn.catlearn.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.catlearn.catlearn.model.Lesson;


@CrossOrigin("http://localhost:4200")
public interface LessonRepository extends JpaRepository<Lesson, Long> {

	
	Optional<Lesson> findByIdAndModule_id(long idLesson,long IdModule);
	
	List<Lesson> findByModule_id(long idModule);
	
	List<Lesson> findByCourse_id(long idCourse);
	
}

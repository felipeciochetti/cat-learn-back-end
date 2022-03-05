package com.example.catlearn.catlearn.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.catlearn.catlearn.model.CustomerCourse;
import com.example.catlearn.catlearn.model.CustomerCourseKey;


@CrossOrigin("http://localhost:4200")
public interface CustomerCourseRepository extends JpaRepository<CustomerCourse, CustomerCourseKey> {

	
	Optional<CustomerCourse> findByPessoa_IdPessoaAndCourse_Id(Long pessoaId, Long courseId);
	
	
	List<CustomerCourse> findByIdPessoaId(Long pessoaId);
	
}

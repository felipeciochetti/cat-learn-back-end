package com.example.catlearn.catlearn.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.catlearn.catlearn.model.Course;
import com.example.catlearn.catlearn.model.Pessoa;


@CrossOrigin("http://localhost:4200")
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


	
}

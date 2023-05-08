package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Student;
public interface StudentRepository extends CrudRepository<Student, Integer> {

	Optional<Student> findBySid(String sid);
	
	//checks to see if sid already exists
		boolean existsBySid(String Sid);
}

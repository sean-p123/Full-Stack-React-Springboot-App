package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Lecturer;

public interface LecturerRepository extends CrudRepository<Lecturer, Integer> {
	
	Optional<Lecturer> findByLid(String lid);

	//checks to see if lid already exists
	boolean existsByLid(String lid);
	
	
	
}

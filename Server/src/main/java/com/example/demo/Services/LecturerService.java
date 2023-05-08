package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Lecturer;
import com.example.demo.repositories.LecturerRepository;

@Service
public class LecturerService {

	@Autowired
	LecturerRepository lr;
	
	public Optional<Lecturer> getLecturer(String lid){
		
		return lr.findByLid(lid);
	}
	  
	public Iterable<Lecturer> getAllLecturers() {
	       
		   return lr.findAll();
	        
	    }
	public void createLecturer(Lecturer l) {
		lr.save(l);
	}

	//checks repository to see if lid exists
	public boolean existsByLid(String lid) {
		return lr.existsByLid(lid);
	}
	
	public Optional<Lecturer> updateLecturer(String lid, Lecturer updatedLecturer) {
//	public void updateLecturer(String lid, Lecturer updatedLecturer) {
	Optional<Lecturer> optionalLecturer = lr.findByLid(lid);
		if (optionalLecturer.isPresent()) {
			Lecturer existingLecturer = optionalLecturer.get();
			existingLecturer.setName(updatedLecturer.getName());
			existingLecturer.setTaxBand(updatedLecturer.getTaxBand());
			existingLecturer.setSalaryScale(updatedLecturer.getSalaryScale());
			lr.save(existingLecturer);
			System.out.println("update methodd " + existingLecturer.toString() + "\n updated: "+ updatedLecturer.toString());
		//	lr.save(updatedLecturer);
		}
		return optionalLecturer;
	}
}

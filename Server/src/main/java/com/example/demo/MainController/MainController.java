package com.example.demo.MainController;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.LecturerService;
import com.example.demo.Services.ModuleService;
import com.example.demo.Services.StudentService;
import com.example.demo.models.Lecturer;
import com.example.demo.models.Student;

import jakarta.validation.Valid;
//@CrossOrigin({"http://localhost:4200", "http://localhost:4200/updateLecturer"})
@RestController
@CrossOrigin("*")
public class MainController {

	@Autowired
	LecturerService ls;
	
	@Autowired
	StudentService ss;
	
	@Autowired
	ModuleService ms;
	
	
	 @GetMapping("lecturer/{lid}")
	    public ResponseEntity<Object> getLecturer(@PathVariable String lid) {
	        Optional<Lecturer> l = ls.getLecturer(lid);
	        if (l.isPresent()) {
	            return ResponseEntity.ok(l.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
	
	@GetMapping(path = "/allLecturers")
	@CrossOrigin("http://localhost:4200")
	@ResponseBody
	public Iterable<Lecturer> getAllLecturers(){
		
		return ls.getAllLecturers();
	}
	@GetMapping(path = "/test")
	public String test() {
		return  "This is a test";
	}
	//gives back error message
	//postlecturers
	@PostMapping(path = "/lecturers")
	public ResponseEntity createLecturer(@RequestBody Lecturer lecturer) {
		//if an ID is entered for the lecturer, display error message
		if(lecturer.getId() != null ) {
			String errorMessage = "ID not allowed in request";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
		//check if lid already exists
		if (ls.existsByLid(lecturer.getLid())) {
		
			String errorMessage = "Lecturer: " + lecturer.getLid() + " already exists";
			com.example.demo.MainController.ErrorResponse er = new com.example.demo.MainController.ErrorResponse();
			er.setTimestamp(LocalDateTime.now());
			er.setStatus(HttpStatus.FORBIDDEN.value());
			er.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
			er.setMessage(errorMessage);
			er.setPath("/lecturers");
	        
	       return ResponseEntity.status(HttpStatus.FORBIDDEN).body(er);
	    }

		// Check if lid is null or empty
	    if (lecturer.getLid() == null || lecturer.getLid().isEmpty()) {
	        String errorMessage = "Lid is required";
	        com.example.demo.MainController.ErrorResponse er = new com.example.demo.MainController.ErrorResponse();
			er.setTimestamp(LocalDateTime.now());
			er.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		//	er.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
			er.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			er.setMessage(errorMessage);
			er.setPath("/lecturers");
			
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	    }
		   // Check if name is null or empty
	    if (lecturer.getName() == null || lecturer.getName().isEmpty()) {
	        String errorMessage = "Name is required";
	        com.example.demo.MainController.ErrorResponse er = new com.example.demo.MainController.ErrorResponse();
			er.setTimestamp(LocalDateTime.now());
			er.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		//	er.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
			er.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			er.setMessage(errorMessage);
			er.setPath("/lecturers");
			
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	    }
	    
		
			ls.createLecturer(lecturer);
			return ResponseEntity.ok("Lecturer created successfully.");
		
	}
	@CrossOrigin("*")
	@PutMapping(path = "/lecturers/{lid}")
	public ResponseEntity<Object> updateLecturer(@PathVariable String lid, @RequestBody Lecturer updatedLecturer) {
		System.out.println("put methodd");
		 Optional<Lecturer> optionalLecturer = ls.getLecturer(lid);
		    if (optionalLecturer.isEmpty()) {
		        // If the lecturer with the given ID doesn't exist, return an error response
		        String errorMessage = "Lecturer not found for ID: " + lid;
		        com.example.demo.MainController.ErrorResponse er = new com.example.demo.MainController.ErrorResponse();
				er.setTimestamp(LocalDateTime.now());
				er.setStatus(HttpStatus.FORBIDDEN.value());
				er.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
				er.setMessage(errorMessage);
				er.setPath("/lecturers");
		       
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
		    }
		    
		    Lecturer existingLecturer = optionalLecturer.get();
		   System.out.println("put updated: "+updatedLecturer.toString() + "\nExisting: "+ existingLecturer.toString() + "\nOptional: "+ optionalLecturer.toString());
		    // Update the properties of the existing lecturer
		   	existingLecturer.setName(updatedLecturer.getName());
		    existingLecturer.setTaxBand(updatedLecturer.getTaxBand());
		    existingLecturer.setSalaryScale(updatedLecturer.getSalaryScale());
		 
		    ls.updateLecturer(lid, existingLecturer);
		    return ResponseEntity.ok("Lecturer updated successfully.");
	}
	
	
	
}

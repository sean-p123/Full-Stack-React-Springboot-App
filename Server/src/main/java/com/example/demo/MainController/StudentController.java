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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.LecturerService;
import com.example.demo.Services.ModuleService;
import com.example.demo.Services.StudentService;
import com.example.demo.models.Lecturer;
import com.example.demo.models.Student;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

	@Autowired
	StudentService ss;
	
	@GetMapping
	public Iterable<Student> getStudents(){
		return ss.getAllStudents();
	}
	
	@DeleteMapping(path = "/{sid}")
	public ResponseEntity deleteStudent(@PathVariable String sid) {
		Optional<Student> s = ss.getStudent(sid);
		
		if(s.isEmpty()) {
			System.out.println("empty");
			String errorMessage = "Student not found with id: "+sid;
			com.example.demo.MainController.ErrorResponse er = new com.example.demo.MainController.ErrorResponse();
			er.setTimestamp(LocalDateTime.now());
			er.setStatus(HttpStatus.FORBIDDEN.value());
			er.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
			er.setMessage(errorMessage);
			er.setPath("/students/"+sid);
	        
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
		}
		Student student = s.get();
		if(!student.getModules().isEmpty()) {
			 // Return an HTTP 500 response with an error message
	        String errorMessage = "Cannot delete the student as they have associated modules.";
	        com.example.demo.MainController.ErrorResponse er = new com.example.demo.MainController.ErrorResponse();
	        er.setTimestamp(LocalDateTime.now());
	        er.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        er.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	        er.setMessage(errorMessage);
	        er.setPath("/students/"+sid);

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
	  
		}
		ss.deleteStudent(sid);
		return ResponseEntity.ok("Student deleted successfully.");
		
	}
}

package com.example.demo.Services;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Lecturer;
import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository sr;
	
	public Optional<Student> getStudent(String sid){
		return sr.findBySid(sid);
	}
	
	public Iterable<Student> getAllStudents() {
	       
		   return sr.findAll();
	        
	    }
	public void createStudent(Student s) {
		sr.save(s);
	}
	
	public void deleteStudent(String sid) {
		 Student student = sr.findBySid(sid)
		            .orElseThrow(() -> new NoSuchElementException("Student not found"));
		    sr.delete(student);
		
	}
}

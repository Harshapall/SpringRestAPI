package com.rest.cruds.controller;

import java.util.List;
import java.util.Optional;

import com.rest.cruds.service.Studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.cruds.CourseRepo.CourseRepo;
import com.rest.cruds.StudentRepo.StudentRepo;
import com.rest.cruds.entity.Student;

@RestController
public class Studentcontroller {

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private Studentservice studentservice;
	
	// Create student details to database
	@PostMapping("/api/students")
	public ResponseEntity<Student>  saveStudent(@RequestBody Student student) {
		studentservice.SaveStudent(student);
		return new ResponseEntity<> (student, HttpStatus.CREATED);
	}
	
	// Get Request
	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<>(studentservice.FindAll(), HttpStatus.OK);
	}
	
	// Get method is to get all items from database
	@GetMapping("/api/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable long id) {
		Optional<Student> student = studentRepo.findById(id);
		return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	

	@PutMapping("/api/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student stud) {
		Student updateds=studentservice.findStudentById(id);
		if(updateds!=null)
		{
			return new ResponseEntity<>(studentRepo.save(updateds), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
		Optional<Student> student = studentRepo.findById(id);
		if (student.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/students/distinctNames")
	public ResponseEntity<List<String>> getDistinctStudentNames() {
		List<String> distinctNames = studentRepo.findDistinctStudentNames();
		return new ResponseEntity<>(distinctNames, HttpStatus.OK);
	}
	
	@GetMapping("/api/students/duplicateNames")
	public ResponseEntity<List<String>> getDuplicates() {
		List<String> duplicates = studentRepo.findDuplicateStudents();
		return new ResponseEntity<>(duplicates, HttpStatus.OK);
	}
	
	@GetMapping("/api/students/findCity")
	public ResponseEntity<List<Student>> getCity() {
		return new ResponseEntity<>(studentRepo.findCity(), HttpStatus.OK);
	}
	
	@GetMapping("/findCourses/{coursename}")
    public ResponseEntity<List<Object[]>> getAllStudentCourses(@PathVariable String coursename) {
        List<Object[]> studentCourses = courseRepo.findAllStudentCourses(coursename);
        if (studentCourses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(studentCourses, HttpStatus.OK);
        }
    }
}

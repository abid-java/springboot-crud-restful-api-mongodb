package com.app.samples.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.samples.springboot.entity.Student;
import com.app.samples.springboot.exception.ResourceNotFoundException;
import com.app.samples.springboot.repository.StudentRepository;
import com.app.samples.springboot.service.StudentSequenceGeneratorService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentSequenceGeneratorService studentSequenceGeneratorService;
	
	//API to create resource - POST Method
	@PostMapping("/createStudent")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student savedStudent = null;
		ResponseEntity<Student> responseEntity = null;
		long studentId = studentSequenceGeneratorService.generateSequence(Student.SEQUENCE_NAME);
		if(student != null) {
			student.setStudentId(studentId);
			savedStudent = studentRepository.save(student);			
			responseEntity = ResponseEntity.ok().body(savedStudent);
		}
		return responseEntity;
	}
	
	//API to get all the resources - GET Method
	@GetMapping("/getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		ResponseEntity<List<Student>> responseEntity = null;
		List<Student> studentsList = studentRepository.findAll();
		responseEntity = ResponseEntity.ok().body(studentsList);
		return responseEntity;
	}
	
	//API to get student - GET Method
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Long studentId) throws ResourceNotFoundException{
		ResponseEntity<Student> responseEntity = null;
		Student existingStudent = studentSequenceGeneratorService.existingStudent(studentId);
		if(existingStudent != null) {
			responseEntity = ResponseEntity.ok().body(existingStudent);
		} else {
			throw new ResourceNotFoundException("Student Not Found with Id : " + studentId);
		}
		return responseEntity;
	}
}

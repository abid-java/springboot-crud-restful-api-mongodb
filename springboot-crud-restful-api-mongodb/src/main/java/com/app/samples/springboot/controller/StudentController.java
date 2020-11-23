package com.app.samples.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.samples.springboot.entity.Student;
import com.app.samples.springboot.repository.StudentRepository;
import com.app.samples.springboot.service.StudentSequenceGeneratorService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentSequenceGeneratorService studentSequenceGeneratorService;
	
	@PostMapping("/createstudent")
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
}

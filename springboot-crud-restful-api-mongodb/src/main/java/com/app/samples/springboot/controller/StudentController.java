package com.app.samples.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.samples.springboot.entity.Address;
import com.app.samples.springboot.entity.Student;
import com.app.samples.springboot.exception.ResourceNotFoundException;
import com.app.samples.springboot.repository.StudentRepository;
import com.app.samples.springboot.service.StudentSequenceGeneratorService;

@RestController
@RequestMapping("/api/v1")
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
	@GetMapping("students")
	public ResponseEntity<List<Student>> getAllStudents() {
		ResponseEntity<List<Student>> responseEntity = null;
		List<Student> studentsList = studentRepository.findAll();
		responseEntity = ResponseEntity.ok().body(studentsList);
		return responseEntity;
	}
	
	//API to get student - GET Method
	@GetMapping("/getStudent/{id}")
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
	
	//API to update student - PUT Method
	@PutMapping("/udpateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long studentId, @RequestBody Student student) {
		ResponseEntity<Student> responseEntity = null;
		// Get existing student information with studentId
		Student existingStudentDB = studentSequenceGeneratorService.existingStudent(studentId);
		Address existingAddressDB = null;
		Address updatedAddress = null;
		if(existingStudentDB != null && student != null) {
			
			//address from DB to updated
			existingAddressDB = existingStudentDB.getAddress();
			
			//new values to be set to DB information
			updatedAddress = student.getAddress();
			
			//update existing address with request body information
			existingAddressDB.setCity(updatedAddress.getCity());
			existingAddressDB.setCountry(updatedAddress.getCountry());
			existingAddressDB.setState(updatedAddress.getState());
			existingAddressDB.setZipCode(updatedAddress.getZipCode());
			
			//update student with updated address
			existingStudentDB.setAddress(existingAddressDB);
			
			//update other information with request body information
			existingStudentDB.setFirstName(student.getFirstName());
			existingStudentDB.setLastName(student.getLastName());
			existingStudentDB.setEmailId(student.getEmailId());			
		} else {
			throw new ResourceNotFoundException("Student Not Found with Id : " + studentId);
		}
		final Student updatedStudent = studentRepository.save(existingStudentDB);
		responseEntity = ResponseEntity.ok().body(updatedStudent);
		return responseEntity;		
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable("id") long studentId) {
		ResponseEntity<Map<String,Boolean>> responseEntity = null;
		Map<String, Boolean> response = null;
		// Get existing student information with studentId
		Student existingStudentDB = studentSequenceGeneratorService.existingStudent(studentId);
		if(existingStudentDB == null) {
			throw new ResourceNotFoundException("Student Not Found with Id : " + studentId);
		}
		studentRepository.delete(existingStudentDB);
		//	studentRepository.deleteById(studentId);
		response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		responseEntity = ResponseEntity.ok().body(response);
		return responseEntity;		
	}
}

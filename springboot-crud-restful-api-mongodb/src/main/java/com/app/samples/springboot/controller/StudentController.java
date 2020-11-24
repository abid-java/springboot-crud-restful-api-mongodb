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

// TODO: Auto-generated Javadoc
/**
 * The Class StudentController.
 */
@RestController
@RequestMapping("/student")
public class StudentController {

	/** The student repository. */
	@Autowired
	private StudentRepository studentRepository;

	/** The student sequence generator service. */
	@Autowired
	private StudentSequenceGeneratorService studentSequenceGeneratorService;
	
	/**
	 * Creates the student.
	 *
	 * @param student the student
	 * @return the response entity
	 */
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
	
	/**
	 * Gets the all students.
	 *
	 * @return the all students
	 */
	//API to get all the resources - GET Method
	@GetMapping("/getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		ResponseEntity<List<Student>> responseEntity = null;
		List<Student> studentsList = studentRepository.findAll();
		responseEntity = ResponseEntity.ok().body(studentsList);
		return responseEntity;
	}
	
	/**
	 * Gets the student by id.
	 *
	 * @param studentId the student id
	 * @return the student by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
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
	
	/**
	 * Update employee.
	 *
	 * @param studentId the student id
	 * @param student the student
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentId, @RequestBody Student student) throws ResourceNotFoundException {
		Student existingStudent = studentSequenceGeneratorService.existingStudent(studentId);
		Address existingAddress = null;
		
		if(existingStudent != null && student != null) {
			existingAddress = existingStudent.getAddress();
			existingAddress.setCity(student.getAddress().getCity());
			existingAddress.setState(student.getAddress().getState());
			existingAddress.setCountry(student.getAddress().getCountry());
			existingAddress.setZipCode(student.getAddress().getZipCode());
			existingStudent.setAddress(existingAddress);
			existingStudent.setFirstName(student.getFirstName());
			existingStudent.setLastName(student.getLastName());
			existingStudent.setEmailId(student.getEmailId());
		} else {
			throw new ResourceNotFoundException("Student Not Found with Id : " + studentId);
		}
		final Student updatedStudent = studentRepository.save(existingStudent);
		return ResponseEntity.ok().body(updatedStudent);
	}
	
	/**
	 * Delete employee.
	 *
	 * @param studentId the student id
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
		ResponseEntity<Map<String, Boolean>> responseEntity = null;
		Map<String, Boolean> response = null;
		Student existingStudent = studentSequenceGeneratorService.existingStudent(studentId);
		
		if(existingStudent != null) {
			studentRepository.delete(existingStudent);
			response = new HashMap<>();
			response.put("DELETED", Boolean.TRUE);
			responseEntity = ResponseEntity.ok().body(response);
		} else {
			throw new ResourceNotFoundException("Student Not Found with Id : " + studentId);
		}
		return responseEntity;
	}
}

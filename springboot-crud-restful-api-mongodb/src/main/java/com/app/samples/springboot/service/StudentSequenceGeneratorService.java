package com.app.samples.springboot.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.app.samples.springboot.entity.SequenceId;
import com.app.samples.springboot.entity.Student;
import com.app.samples.springboot.repository.StudentRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentSequenceGeneratorService.
 */
@Service
public class StudentSequenceGeneratorService {
	
	/** The mongo operations. */
	private MongoOperations mongoOperations;
	
	/** The student repository. */
	@Autowired
	private StudentRepository studentRepository;

	/**
	 * Instantiates a new student sequence generator service.
	 *
	 * @param mongoOperations the mongo operations
	 */
	@Autowired
	public StudentSequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	/**
	 * Generate sequence.
	 *
	 * @param sequenceName the sequence name
	 * @return the long
	 */
	public long generateSequence(String sequenceName) {
		SequenceId sequenceId = null;
		long counter;
		Query query = new Query(Criteria.where("_id").is(sequenceName));
		Update update = new Update();
		update.inc("sequence", 1);
		FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
		findAndModifyOptions.upsert(true);
		findAndModifyOptions.returnNew(true);
		sequenceId = mongoOperations.findAndModify(query, update, findAndModifyOptions, SequenceId.class);
		if(!Objects.isNull(sequenceId)) {
			counter = sequenceId.getSequence();
		}else {
			counter = 1;
		}
		return counter;
	}
	
	/**
	 * Existing student.
	 *
	 * @param studentId the student id
	 * @return the student
	 */
	public Student existingStudent(Long studentId) {
		Student existingStudent = null;
		List<Student> studentsList = studentRepository.findAll();
		for(Student student : studentsList) {
			if(student != null) {
				if(studentId.equals(student.getStudentId())) {
					existingStudent = student;
				} 
			}
		}
		return existingStudent;
	}
	
	

}

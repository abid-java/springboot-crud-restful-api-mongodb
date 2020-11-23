package com.app.samples.springboot.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.app.samples.springboot.entity.SequenceId;
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
	
	

}

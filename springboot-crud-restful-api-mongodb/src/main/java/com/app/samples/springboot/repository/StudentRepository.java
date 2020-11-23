package com.app.samples.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.samples.springboot.entity.Student;

/**
 * The Interface StudentRepository.
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, Long>{

}

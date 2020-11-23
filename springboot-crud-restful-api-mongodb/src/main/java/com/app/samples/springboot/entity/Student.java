package com.app.samples.springboot.entity;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Student.
 */
@Document(collection = "student")
public class Student {

	/** The Constant SEQUENCE_NAME. */
	@Transient
	public static final String SEQUENCE_NAME = "student_sequence";

	/** The student id. */
	@Id
	private long studentId;
	
	/** The first name. */
	@NotBlank
	@Size(max=100)
	@Indexed(unique=false)
	private String firstName;
	
	/** The last name. */
	@NotBlank
	@Size(max=100)
	@Indexed(unique=false)
	private String lastName;
	
	/** The email id. */
	@NotBlank
	@Size(max=100)
	@Indexed(unique=false)
	private String emailId;
	
	/** The address. */
	private Address address;

	/**
	 * Instantiates a new student.
	 */
	public Student() {
		super();
	}

	/**
	 * Instantiates a new student.
	 *
	 * @param studentId the student id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param emailId the email id
	 * @param address the address
	 */
	public Student(long studentId, @NotBlank @Size(max = 100) String firstName,
			@NotBlank @Size(max = 100) String lastName, @NotBlank @Size(max = 100) String emailId, Address address) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.address = address;
	}

	/**
	 * Gets the student id.
	 *
	 * @return the student id
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * Sets the student id.
	 *
	 * @param studentId the new student id
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
}

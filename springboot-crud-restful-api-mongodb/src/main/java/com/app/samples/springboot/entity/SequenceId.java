package com.app.samples.springboot.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class SequenceId.
 */
@Document(collection = "studentmongo_sequences")
public class SequenceId {
	
	/** The id. */
	@Id
	private String id;

	/** The sequence. */
	private long sequence;

	/**
	 * Instantiates a new sequence id.
	 */
	public SequenceId() {
		super();
	}

	/**
	 * Instantiates a new sequence id.
	 *
	 * @param id the id
	 * @param sequence the sequence
	 */
	public SequenceId(String id, long sequence) {
		super();
		this.id = id;
		this.sequence = sequence;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the sequence.
	 *
	 * @return the sequence
	 */
	public long getSequence() {
		return sequence;
	}

	/**
	 * Sets the sequence.
	 *
	 * @param sequence the new sequence
	 */
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	
}
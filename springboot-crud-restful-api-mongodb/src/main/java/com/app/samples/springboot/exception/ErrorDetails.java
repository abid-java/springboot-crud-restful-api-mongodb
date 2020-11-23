package com.app.samples.springboot.exception;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorDetails.
 */
public class ErrorDetails {
	
	/** The time stamp. */
	private Date timeStamp;
	
	/** The message. */
	private String message;
	
	/** The details. */
	private String details;
	
	/**
	 * Instantiates a new error details.
	 */
	public ErrorDetails() {
		super();
	}

	/**
	 * Instantiates a new error details.
	 *
	 * @param timeStamp the time stamp
	 * @param message the message
	 * @param details the details
	 */
	public ErrorDetails(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp.
	 *
	 * @param timeStamp the new time stamp
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Sets the details.
	 *
	 * @param details the new details
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
}
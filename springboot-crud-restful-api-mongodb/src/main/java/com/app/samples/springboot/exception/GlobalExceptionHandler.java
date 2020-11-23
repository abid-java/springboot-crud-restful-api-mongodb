package com.app.samples.springboot.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handle resource not found exception.
	 *
	 * @param resourceNotFoundException the resource not found exception
	 * @param webRequest the web request
	 * @return the response entity
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,WebRequest webRequest) {
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), resourceNotFoundException.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handle global exception.
	 *
	 * @param exception the exception
	 * @param webRequest the web request
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception,WebRequest webRequest) {
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
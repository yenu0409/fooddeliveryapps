package com.project.validator;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.project.exception.ResourceNotFound;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class ValidationHandler {
	
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus status,WebRequest request){
	
		Map<String,String> errors=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError) error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldName, message);
			
		});
		return new ResponseEntity<Object> (errors,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ResourceNotFound.class) 
	public ResponseEntity<String>
	  handleResourceNotFound(ResourceNotFound resourceNotFoundException) {
	  return new
	  ResponseEntity<String>(resourceNotFoundException.getMessage(),HttpStatus.BAD_REQUEST); }
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class) 
	  protected ResponseEntity<String>  handleConstraintViolationException(SQLIntegrityConstraintViolationException ex, 
			  WebRequest request) {
	    String error = ex.getLocalizedMessage();
	    
	  return new ResponseEntity<String>("Oops duplicate Entry of the data !", HttpStatus.BAD_REQUEST);
	  }
}


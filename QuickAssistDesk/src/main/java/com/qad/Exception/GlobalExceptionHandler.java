package com.qad.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> handleGlobalExceptions(Exception se,WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(wr.getDescription(false));
		myErrorDetails.setMesseage(se.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> handleValidationGlobalExceptions(MethodArgumentNotValidException se){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(se.getBindingResult().getFieldError().getDefaultMessage());
		myErrorDetails.setMesseage("Validation Error"); // clear exception from IA
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> handleCustomerExceptions(CustomerException ce,WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(wr.getDescription(false));
		myErrorDetails.setMesseage(ce.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(IssueException.class)
	public ResponseEntity<MyErrorDetails> handleIssueExceptions(IssueException ie,WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(wr.getDescription(false));
		myErrorDetails.setMesseage(ie.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(DepartmentException.class)
	public ResponseEntity<MyErrorDetails> handleDepartmentExceptions(DepartmentException de,WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(wr.getDescription(false));
		myErrorDetails.setMesseage(de.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(OperatorException.class)
	public ResponseEntity<MyErrorDetails> handleOperatorExceptions(OperatorException oe,WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(wr.getDescription(false));
		myErrorDetails.setMesseage(oe.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CallException.class)
	public ResponseEntity<MyErrorDetails> handleCartExceptions(CallException ce,WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(wr.getDescription(false));
		myErrorDetails.setMesseage(ce.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(SolutionException.class)
	public ResponseEntity<MyErrorDetails> handleAdminExceptions(SolutionException se,WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(wr.getDescription(false));
		myErrorDetails.setMesseage(se.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> handleAdminExceptions(AdminException ae,WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(wr.getDescription(false));
		myErrorDetails.setMesseage(ae.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> handleLoginExceptions(LoginException le,WebRequest wr){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		
		myErrorDetails.setLocalDateTime(LocalDateTime.now());
		myErrorDetails.setDescription(wr.getDescription(false));
		myErrorDetails.setMesseage(le.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
}

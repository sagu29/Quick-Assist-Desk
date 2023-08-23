package com.qad.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	//customer exception
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerExceptionHandler(CustomerException ce, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("customerException Occour!");
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	//login exception
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginExceptionHandler(LoginException ce, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("LoginException Occour!");
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	// driver exception
	@ExceptionHandler(CallException.class)
	public ResponseEntity<MyErrorDetails> CallExceptionHandler(CallException ce, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("CallException Occour!");
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	// admin exception
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> AdminExceptionHandler(AdminException ce, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("AdminException Occour!");
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	//cab exception
	@ExceptionHandler(IssueException.class)
	public ResponseEntity<MyErrorDetails> IssueExceptionHandler(IssueException ie, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("IssueException Occour!");
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	//booking exception
	@ExceptionHandler(OperatorException.class)
	public ResponseEntity<MyErrorDetails> operaterExceptionHandler(OperatorException oe, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(oe.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("operaterException Occour!");
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> anyExceptionHandler(Exception ie, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("Exception Occour!");
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException ie, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("NotHandlerException Occour!");
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myInvalidDataExpHandler(MethodArgumentNotValidException me) {
		
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());
		log.error("ValidationException Occour!");
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
		
	}

	@ExceptionHandler(FeedbackException.class)
	public ResponseEntity<MyErrorDetails> FeedbackExceptionHandler(FeedbackException re, WebRequest webReq){
		
		MyErrorDetails red = new MyErrorDetails();
		red.setTimestamp(LocalDateTime.now());
		red.setMessage(re.getMessage());
		red.setDetails(webReq.getDescription(false));
		log.error("FeedbackException Occour!");
		return new ResponseEntity<MyErrorDetails>(red, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(CurrentOperatorSessionException.class)
	public ResponseEntity<MyErrorDetails> CurrentOperatorSessionExceptionHandler(CurrentOperatorSessionException re, WebRequest webReq){
		
		MyErrorDetails red = new MyErrorDetails();
		red.setTimestamp(LocalDateTime.now());
		red.setMessage(re.getMessage());
		red.setDetails(webReq.getDescription(false));
		log.error("CurrentOperatorSessionException Occour!");
		return new ResponseEntity<MyErrorDetails>(red, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NotfoundException.class)
	public ResponseEntity<MyErrorDetails> NotFoundExceptionHandler(NotfoundException ne,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ne.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("NotFoundException Occour!");
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DepartmentException.class)
	public ResponseEntity<MyErrorDetails> DepartmentExceptionHandler(DepartmentException ne,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ne.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("DepartmentException Occour!");
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(SolutionException.class)
	public ResponseEntity<MyErrorDetails> SolutionExceptionHandler(SolutionException ne,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ne.getMessage());
		err.setDetails(req.getDescription(false));
		log.error("SolutionException Occour!");
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
}

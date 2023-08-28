package com.qad.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qad.Entity.CurrentOperatorSession;
import com.qad.Entity.Customer;
import com.qad.Entity.Issue;
import com.qad.Entity.Login;
import com.qad.Exception.LoginException;
import com.qad.Exception.OperatorException;
import com.qad.Service.OperatorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/operator")
public class OperatorController {
	
	@Autowired
	private OperatorService operatorService ;
	
	
	
	@PostMapping("/operatorLogin")
	public ResponseEntity<CurrentOperatorSession> loginOperator(@RequestBody Login login) throws LoginException, OperatorException {
		
		CurrentOperatorSession result = operatorService.loginOperator(login);
			
		return new ResponseEntity<CurrentOperatorSession>(result,HttpStatus.OK );
	}
	
	
	
	@PostMapping("/addIssue")
	public ResponseEntity<Issue> addCustomerIssueHandler(@RequestBody @Valid Issue issue) throws OperatorException{
		
		Issue i = operatorService.addCustomerIssue(issue);
		
		return new ResponseEntity<Issue>(i,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/modifyIssue/{issueId}")
	public ResponseEntity<Issue> modifyIssueHandler(@RequestBody @Valid Issue issue, @PathVariable Integer issueId)throws OperatorException {
		
		Issue i = operatorService.modifyCustomerIssue(issue, issueId);
		
		return new ResponseEntity<Issue>(i,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/close/{issueId}")
    public ResponseEntity<String> closeCustomerIssue(@RequestBody Issue issue, @PathVariable Integer issueId) throws OperatorException {
        
            operatorService.closeCustomerIssue(issue, issueId);
            
            return ResponseEntity.status(HttpStatus.OK).body("Issue closed successfully");
       
    }

	
	
	
	@GetMapping("/customerid/{customerId}")
	public ResponseEntity<Customer> getCustomerByIdHandler(@PathVariable @Valid Integer customerId)throws OperatorException {
		
		Customer c = operatorService.findCustomerById(customerId);
		
		return new ResponseEntity<Customer>(c,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/customername/{name}")
	public ResponseEntity<List<Customer>> getCustomerByFirstNameHandler(@PathVariable @Valid String name) throws OperatorException{
		
		List<Customer> cList =  operatorService.findCustomerByName(name);
		
		return new ResponseEntity<List<Customer>>(cList,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/customeremail/{email}")
	public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable @Valid String email)throws OperatorException {
		
		Customer c =  operatorService.findCustomerByEmail(email);
		
		return new ResponseEntity<Customer>(c,HttpStatus.OK);
	}
	@GetMapping("/AllIssue")
	public ResponseEntity<List<Issue>> findAllIssue() throws OperatorException{
		List<Issue> list=operatorService.findAllIssue();
		return new ResponseEntity<List<Issue>>(list,HttpStatus.OK);
	} 
	
}

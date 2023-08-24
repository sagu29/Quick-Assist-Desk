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
import org.springframework.web.bind.annotation.RestController;

import com.qad.Entity.CurrentCustomerSession;
import com.qad.Entity.Customer;
import com.qad.Entity.Issue;
import com.qad.Entity.IssueStatus;
import com.qad.Entity.Login;
import com.qad.Exception.AdminException;
import com.qad.Exception.CustomerException;
import com.qad.Exception.LoginException;
import com.qad.Service.CustomerService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		
		Customer savedCustomer= customerService.registerCustomer(customer);
		
		return new ResponseEntity<Customer>(savedCustomer,HttpStatus.CREATED);
	}
	
	

	@PostMapping("/loginCustomer")
	public ResponseEntity<CurrentCustomerSession> loginAdmin(@RequestBody Login login) throws LoginException, CustomerException {
		
		CurrentCustomerSession result = customerService.loginCustomer(login);
			
		return new ResponseEntity<CurrentCustomerSession>(result,HttpStatus.OK );
	}
	
	

	@GetMapping("/viewIssuesById/{issueId}")
	public ResponseEntity<Issue> viewIssuesByIdHandler(@PathVariable Integer issueId) throws CustomerException {
		
		Issue issue = customerService.viewIssuesById(issueId);
		
		return new ResponseEntity<>(issue, HttpStatus.OK);
	}
	
	

	@PutMapping("/{issueId}/reopen")
    public ResponseEntity<Issue> reopenIssue(@PathVariable Integer issueId) throws CustomerException {
      
            Issue reopenedIssue = customerService.reOpenIssue(issueId);
            reopenedIssue.setStatus(IssueStatus.PENDING); 
            return ResponseEntity.ok(reopenedIssue);
      
    }
	
	

	@GetMapping("/allIssues/{customerId}")
	public ResponseEntity<List<Issue>> viewAllIssuesHandler(@PathVariable Integer customerId) throws CustomerException {
		
		List<Issue> issues = customerService.viewAllIssues(customerId);
		
		return new ResponseEntity<List<Issue>>(issues, HttpStatus.OK);
	}
	
	

//	@PutMapping("/customer/changePassword")
//	public ResponseEntity<String> changePasswordHandler(@RequestBody Customer customer) throws CustomerException {
//		String passwordMessage = customerService.changePassword(new Login(customer.getEmail(), customer.getPassword()));
//		return new ResponseEntity<>(passwordMessage, HttpStatus.OK);
//	}
//
//	
//	
//	@PutMapping("/customer/{customerId}/forgotPassword")
//	public ResponseEntity<String> forgotPasswordHandler(@PathVariable Integer customerId) throws CustomerException {
//		String passwordResetMessage = customerService.forgotPassword(customerId);
//		return new ResponseEntity<>(passwordResetMessage, HttpStatus.OK);
//	}
//	
//	
//
//	@PutMapping("/customer/{customerId}/emailPassword")
//	public ResponseEntity<String> emailPasswordHandler(@PathVariable Integer customerId) throws CustomerException {
//		String emailPasswordMessage = customerService.emailPassword(customerId);
//		return new ResponseEntity<>(emailPasswordMessage, HttpStatus.OK);
//	}
	
}

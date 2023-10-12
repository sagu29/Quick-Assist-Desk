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

/**
 * Controller class for handling customer-related operations.
 */
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Register a new customer in the system.
     *
     * @param customer The customer to be registered.
     * @return The registered customer.
     * @throws CustomerException If there's an issue with registering the customer.
     */
    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) throws CustomerException {

        Customer savedCustomer = customerService.registerCustomer(customer);

        return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
    }

    /**
     * Log in a customer using the provided login credentials.
     *
     * @param login The login credentials, including a username and password.
     * @return The current customer session if the login is successful.
     * @throws LoginException If there's an issue related to the customer account.
     * @throws CustomerException If there's an issue with the login process.
     */
    @PostMapping("/loginCustomer")
    public ResponseEntity<CurrentCustomerSession> loginAdmin(@RequestBody Login login) throws LoginException, CustomerException {

        CurrentCustomerSession result = customerService.loginCustomer(login);

        return new ResponseEntity<CurrentCustomerSession>(result, HttpStatus.OK);
    }

    /**
     * View an issue by its ID.
     *
     * @param issueId The ID of the issue to be viewed.
     * @return The issue with the specified ID.
     * @throws CustomerException If there's an issue with finding the issue.
     */
    @GetMapping("/viewIssuesById/{issueId}")
    public ResponseEntity<Issue> viewIssuesByIdHandler(@PathVariable Integer issueId) throws CustomerException {

        Issue issue = customerService.viewIssuesById(issueId);

        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    /**
     * Reopen a previously resolved issue based on its ID.
     *
     * @param issueId The ID of the issue to be reopened.
     * @return The reopened issue.
     * @throws CustomerException If there's an issue with reopening the issue.
     */
    @PutMapping("/{issueId}/reopen")
    public ResponseEntity<Issue> reopenIssue(@PathVariable Integer issueId) throws CustomerException {

        Issue reopenedIssue = customerService.reOpenIssue(issueId);
        reopenedIssue.setStatus(IssueStatus.PENDING);
        return ResponseEntity.ok(reopenedIssue);
    }

    /**
     * View all issues associated with a specific customer.
     *
     * @param customerId The ID of the customer for whom issues should be retrieved.
     * @return A list of issues associated with the specified customer.
     * @throws CustomerException If there's an issue with finding the customer's issues.
     */
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

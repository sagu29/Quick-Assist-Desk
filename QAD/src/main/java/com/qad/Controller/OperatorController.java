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

/**
 * Controller class for handling operator-related operations.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    /**
     * Log in an operator using the provided login credentials.
     *
     * @param login The login credentials, including a username and password.
     * @return The current operator session if the login is successful.
     * @throws LoginException If there's an issue related to the operator account.
     * @throws OperatorException If there's an issue with the login process.
     */
    @PostMapping("/operatorLogin")
    public ResponseEntity<CurrentOperatorSession> loginOperator(@RequestBody Login login) throws LoginException, OperatorException {

        CurrentOperatorSession result = operatorService.loginOperator(login);

        return new ResponseEntity<CurrentOperatorSession>(result, HttpStatus.OK);
    }

    /**
     * Add a new issue on behalf of a customer.
     *
     * @param issue The issue to be added.
     * @return The added issue.
     * @throws OperatorException If there's an issue with adding the issue.
     */
    @PostMapping("/addIssue")
    public ResponseEntity<Issue> addCustomerIssueHandler(@RequestBody @Valid Issue issue) throws OperatorException {

        Issue i = operatorService.addCustomerIssue(issue);

        return new ResponseEntity<Issue>(i, HttpStatus.CREATED);
    }

    /**
     * Modify an existing customer issue.
     *
     * @param issue    The modified issue information.
     * @param issueId  The ID of the issue to be modified.
     * @return The modified issue.
     * @throws OperatorException If there's an issue with modifying the issue.
     */
    @PutMapping("/modifyIssue/{issueId}")
    public ResponseEntity<Issue> modifyIssueHandler(@RequestBody @Valid Issue issue, @PathVariable Integer issueId) throws OperatorException {

        Issue i = operatorService.modifyCustomerIssue(issue, issueId);

        return new ResponseEntity<Issue>(i, HttpStatus.OK);
    }

    /**
     * Close an existing customer issue.
     *
     * @param issue    The issue to be closed.
     * @param issueId  The ID of the issue to be closed.
     * @return A message indicating the success of closing the issue.
     * @throws OperatorException If there's an issue with closing the issue.
     */
    @PutMapping("/close/{issueId}")
    public ResponseEntity<String> closeCustomerIssue(@RequestBody Issue issue, @PathVariable Integer issueId) throws OperatorException {
        operatorService.closeCustomerIssue(issue, issueId);
        return ResponseEntity.status(HttpStatus.OK).body("Issue closed successfully");
    }

    /**
     * Find a customer by their ID.
     *
     * @param customerId The ID of the customer to be found.
     * @return The customer with the specified ID.
     * @throws OperatorException If there's an issue with finding the customer.
     */
    @GetMapping("/customerid/{customerId}")
    public ResponseEntity<Customer> getCustomerByIdHandler(@PathVariable @Valid Integer customerId) throws OperatorException {

        Customer c = operatorService.findCustomerById(customerId);

        return new ResponseEntity<Customer>(c, HttpStatus.OK);
    }

    /**
     * Find customers by their first name.
     *
     * @param name The first name of customers to be found.
     * @return A list of customers with the specified first name.
     * @throws OperatorException If there's an issue with finding customers by name.
     */
    @GetMapping("/customername/{name}")
    public ResponseEntity<List<Customer>> getCustomerByFirstNameHandler(@PathVariable @Valid String name) throws OperatorException {

        List<Customer> cList = operatorService.findCustomerByName(name);

        return new ResponseEntity<List<Customer>>(cList, HttpStatus.OK);
    }

    /**
     * Find a customer by their email address.
     *
     * @param email The email address of the customer to be found.
     * @return The customer with the specified email address.
     * @throws OperatorException If there's an issue with finding the customer by email.
     */
    @GetMapping("/customeremail/{email}")
    public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable @Valid String email) throws OperatorException {

        Customer c = operatorService.findCustomerByEmail(email);

        return new ResponseEntity<Customer>(c, HttpStatus.OK);
    }

    /**
     * Retrieve a list of all issues.
     *
     * @return A list of all issues in the system.
     * @throws OperatorException If there's an issue with retrieving the list of issues.
     */
    @GetMapping("/AllIssue")
    public ResponseEntity<List<Issue>> findAllIssue() throws OperatorException {
        List<Issue> list = operatorService.findAllIssue();
        return new ResponseEntity<List<Issue>>(list, HttpStatus.OK);
    }
}

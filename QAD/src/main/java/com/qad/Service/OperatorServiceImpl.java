package com.qad.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qad.Entity.CurrentOperatorSession;
import com.qad.Entity.Customer;
import com.qad.Entity.Issue;
import com.qad.Entity.IssueStatus;
import com.qad.Entity.Login;
import com.qad.Entity.Operator;
import com.qad.Exception.LoginException;
import com.qad.Exception.OperatorException;
import com.qad.Repository.CurrentOperatorSessionRepository;
import com.qad.Repository.CustomerRepository;
import com.qad.Repository.IssueRepository;
import com.qad.Repository.OperatorRepository;

import net.bytebuddy.utility.RandomString;

/**
 * Service class for managing operator-related operations.
 */
@Service
public class OperatorServiceImpl implements OperatorService {
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OperatorRepository operatorRepository;
	
	@Autowired
	private CurrentOperatorSessionRepository currentOperatorSessionRepository;

	/**
	 * Log in an operator using the provided login credentials.
	 *
	 * @param login The login credentials, including a username and password.
	 * @return The current operator session if the login is successful.
	 * @throws OperatorException If there's an issue related to the operator account.
	 * @throws LoginException If the login credentials are invalid, the user is already logged in, or the password is incorrect.
	 */
	@Override
	public CurrentOperatorSession loginOperator(Login login) throws OperatorException, LoginException {

		// Find the existing operator by their username
		Operator existingOperator = operatorRepository.findByUsername(login.getUsername());
		
		// If the operator doesn't exist, throw a LoginException
		if (existingOperator == null) {
			throw new LoginException("Please enter a valid username number");
		}
		
		// Try to find the current operator session for the existing operator
		Optional<CurrentOperatorSession> validOperatorSessionOpt = currentOperatorSessionRepository.findById(existingOperator.getOperatorId());
		
		// If a session already exists, throw a LoginException
		if (validOperatorSessionOpt.isPresent()) {
			throw new LoginException("User already logged in with this username");
		}
		
		// Check if the password matches the operator's password
		if (existingOperator.getPassword().equals(login.getPassword())) {
			// Generate a session key (e.g., a random string)
			String key = RandomString.make(6);
			
			// Create a new current operator session
			CurrentOperatorSession currentOperatorSession = new CurrentOperatorSession(existingOperator.getOperatorId(), key, LocalDateTime.now());
			
			// Save the current operator session
			currentOperatorSessionRepository.save(currentOperatorSession);
			
			return currentOperatorSession;
		} else {
			throw new LoginException("Please enter a valid password");
		}
	}

	/**
	 * Add a customer issue to the system.
	 *
	 * @param issue The issue to be added.
	 * @return The added issue.
	 * @throws OperatorException If there's an issue with adding the issue.
	 */
	@Override
	public Issue addCustomerIssue(Issue issue) throws OperatorException {
		
		// Check if the provided issue is null
		if (issue == null) {
			throw new OperatorException("Issue can't be null");
		}

		// Save the issue to the repository
		return issueRepository.save(issue);
	}

	/**
	 * Modify a customer issue based on the provided issue details and issue ID.
	 *
	 * @param issue The updated issue.
	 * @param issueId The ID of the issue to be modified.
	 * @return The modified issue.
	 * @throws OperatorException If there's an issue with modifying the issue.
	 */
	@Override
	public Issue modifyCustomerIssue(Issue issue, Integer issueId) throws OperatorException {

		// Check if the provided issue is null
		if (issue == null) {
			throw new OperatorException("Issue can't be null");
		}

		// Try to find the existing issue by its ID
		Optional<Issue> existingIssue = issueRepository.findById(issueId);
		
		// If the issue doesn't exist, throw an OperatorException
		if(!existingIssue.isPresent()) 
			throw new OperatorException("Issue doesn't exist with the given issueId");
		
		// Get the existing issue and update its properties
		Issue issueToUpdate = existingIssue.get();
	    issueToUpdate.setIssueType(issue.getIssueType());
	    issueToUpdate.setDescription(issue.getDescription());
	    issueToUpdate.setStatus(issue.getStatus());

	    // Save the updated issue
	    Issue updatedIssue = issueRepository.save(issueToUpdate);
		
		return updatedIssue;
	}	

	/**
	 * Close a customer issue with the given issue ID.
	 *
	 * @param issue The issue to be closed.
	 * @param issueId The ID of the issue to be closed.
	 * @throws OperatorException If there's an issue with closing the issue.
	 */
	@Override
	public void closeCustomerIssue(Issue issue, Integer issueId) throws OperatorException {
        
		// Try to find the existing issue by its ID or throw an exception if not found
		Issue existingIssue = issueRepository.findById(issueId)
                .orElseThrow(() -> new OperatorException("Issue not found with id: " + issueId));

        // Check if the issue status is pending and change it to resolved
        if (existingIssue.getStatus() == IssueStatus.PENDING) {
            existingIssue.setStatus(IssueStatus.RESOLVED);
            issueRepository.save(existingIssue);
        } else {
            throw new OperatorException("Issue is not in PENDING status");
        }
    }

	/**
	 * Find a customer by their ID.
	 *
	 * @param customerId The ID of the customer to be found.
	 * @return The customer with the specified ID.
	 * @throws OperatorException If there's an issue with finding the customer.
	 */
	@Override
	public Customer findCustomerById(Integer customerId) throws OperatorException {
		
		// Check if the provided customerId is null
		if (customerId == null) {
			throw new OperatorException("CustomerId can't be null");
		}

		// Try to find the customer by their ID in the repository
		Optional<Customer> existingCustomer = customerRepository.findById(customerId);
		
		// If the customer doesn't exist, throw an OperatorException
		if(existingCustomer.isEmpty()) 
			throw new OperatorException("No customer exists with given customerId");
		
		return existingCustomer.get();
	}

	/**
	 * Find customers with a specific name.
	 *
	 * @param name The name of the customers to be found.
	 * @return A list of customers with the specified name.
	 * @throws OperatorException If there's an issue with finding the customers.
	 */
	@Override
	public List<Customer> findCustomerByName(String name) throws OperatorException {
		
		// Check if the provided name is null or empty
		if (name == null || name.isEmpty()) {
            throw new OperatorException("Name cannot be empty");
        }

        // Find customers by name in the repository
        List<Customer> customers = customerRepository.findByName(name);

        return customers;
	}
	
	/**
	 * Find a customer by their email address.
	 *
	 * @param email The email address of the customer to be found.
	 * @return The customer with the specified email address.
	 * @throws OperatorException If there's an issue with finding the customer.
	 */
	@Override
	public Customer findCustomerByEmail(String email) throws OperatorException {
		
		// Check if the provided email is null
		if (email == null) {
			throw new OperatorException("CustomerId can't be null");
		}

		// Try to find the customer by their email in the repository
		Optional<Customer> existingCustomer = customerRepository.findByEmail(email);
		
		// If the customer doesn't exist, throw an OperatorException
		if(existingCustomer.isEmpty()) 
			throw new OperatorException("No customer exists with given email");
		
		return existingCustomer.get();
	}

	/**
	 * Send an intimidation email to a customer with a specific issue and customer ID.
	 *
	 * @param issueId The ID of the issue associated with the email.
	 * @param customerId The ID of the customer to whom the email is sent.
	 * @return A message indicating the success of sending the intimidation email.
	 * @throws OperatorException If there's an issue with sending the email.
	 */
	@Override
	public String sendIntimidationEmailToCustomer(Integer issueId, Integer customerId) throws OperatorException {
		
		return null;
	}

	/**
	 * Send a modification email to a customer with a specific issue and customer ID.
	 *
	 * @param issueId The ID of the issue associated with the email.
	 * @param customerId The ID of the customer to whom the email is sent.
	 * @return A message indicating the success of sending the modification email.
	 * @throws OperatorException If there's an issue with sending the email.
	 */
	@Override
	public String sendModificationEmailToCustomer(Integer issueId, Integer customerId) throws OperatorException {
		
		return null;
	}

	/**
	 * Lock a customer account with the specified customer ID.
	 *
	 * @param customerId The ID of the customer whose account will be locked.
	 * @return `true` if the account is successfully locked, `false` otherwise.
	 * @throws OperatorException If there's an issue with locking the account.
	 */
	@Override
	public boolean lockCustomer(Integer customerId) throws OperatorException {
		return false;
	}

	/**
	 * Retrieve a list of all issues in the system.
	 *
	 * @return A list of all issues in the system.
	 * @throws OperatorException If there's an issue with retrieving the issues.
	 */
	@Override
	public List<Issue> findAllIssue() throws OperatorException {
		// Find and return all issues in the repository
		List<Issue> list = issueRepository.findAll();
		return list;
	}
}

	

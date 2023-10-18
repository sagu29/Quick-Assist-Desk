package com.qad.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qad.Entity.CurrentCustomerSession;
import com.qad.Entity.Customer;
import com.qad.Entity.Issue;
import com.qad.Entity.IssueStatus;
import com.qad.Entity.Login;
import com.qad.Exception.CustomerException;
import com.qad.Exception.LoginException;
import com.qad.Repository.CurrentCustomerSessionRepository;
import com.qad.Repository.CustomerRepository;
import com.qad.Repository.IssueRepository;

import net.bytebuddy.utility.RandomString;

/**
 * Service class for managing customer-related operations.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private CurrentCustomerSessionRepository currentCustomerSessionRepository;

	/**
	 * Register a new customer.
	 *
	 * @param customer The customer to be registered.
	 * @return The registered customer.
	 * @throws CustomerException If there's an issue with the registration process.
	 */
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		// Check if the provided customer is null
		if (customer == null) {
			throw new CustomerException("Customer can't be null");
		}
		// Save the customer to the repository
		return customerRepository.save(customer);
	}
	
	/**
	 * Log in a customer using the provided login credentials.
	 *
	 * @param login The login credentials, including a username and password.
	 * @return The current customer session if the login is successful.
	 * @throws CustomerException If there's an issue related to the customer account.
	 * @throws LoginException If the login credentials are invalid, the user is already logged in, or the password is incorrect.
	 */
	@Override
	public CurrentCustomerSession loginCustomer(Login login) throws CustomerException, LoginException {
		// Find the existing customer by their username
		Customer existingCustomer = customerRepository.findByUsername(login.getUsername());
		// If the customer doesn't exist, throw a LoginException
		if (existingCustomer == null) {
			throw new LoginException("Please enter a valid username");
		}	
		// Try to find the current customer session for the existing customer
		Optional<CurrentCustomerSession> validCustomerSessionOpt = currentCustomerSessionRepository.findById(existingCustomer.getCustomerId());
		// If a session already exists, throw a LoginException
		if (validCustomerSessionOpt.isPresent()) {
			throw new LoginException("User already logged in with this username");
		}
		// Check if the password matches the customer's password
		if (existingCustomer.getPassword().equals(login.getPassword())) {
			// Generate a session key (e.g., a random string)
			String key = RandomString.make(6);
			// Create a new current customer session
			CurrentCustomerSession currentCustomerSession = new CurrentCustomerSession(existingCustomer.getCustomerId(), key, LocalDateTime.now());
			// Save the current customer session
			currentCustomerSessionRepository.save(currentCustomerSession);
			return currentCustomerSession;
		} else {
			throw new LoginException("Please enter a valid password");
		}
	}

	/**
	 * View an issue by its ID.
	 *
	 * @param issueId The ID of the issue to be viewed.
	 * @return The issue with the specified ID.
	 * @throws CustomerException If there's an issue related to the issue or customer.
	 */
	@Override
	public Issue viewIssuesById(Integer issueId) throws CustomerException {
		// Check if the provided issueId is null
		if (issueId == null) {
			throw new CustomerException("IssueId can't be null");
		}
		// Try to find the issue by its ID in the repository
		Optional<Issue> issue = issueRepository.findById(issueId);
		// If the issue doesn't exist, throw a CustomerException
		if (issue.isEmpty()) {
			throw new CustomerException("Issue doesn't exist with the given issueId for the given Customer");
		}
		return issue.get();
	}

	/**
	 * Reopen a previously resolved issue.
	 *
	 * @param issueId The ID of the issue to be reopened.
	 * @return The updated issue after reopening.
	 * @throws CustomerException If the issue is not found or cannot be reopened.
	 */
	@Override
	public Issue reOpenIssue(Integer issueId) throws CustomerException {
		// Try to find the issue by its ID in the repository or throw an exception if not found
		Issue issue = issueRepository.findById(issueId)
	            .orElseThrow(() -> new CustomerException("Issue not found with ID: " + issueId));
		// Check if the issue status is resolved and change it to pending
		if (issue.getStatus() == IssueStatus.RESOLVED) {
			issue.setStatus(IssueStatus.PENDING);
			// Save the updated issue
			return issueRepository.save(issue);
		} else {
			throw new CustomerException("Cannot reopen issue. The issue is not in the RESOLVED state.");
		}
	}

	/**
	 * View all issues associated with a specific customer.
	 *
	 * @param customerId The ID of the customer whose issues are to be viewed.
	 * @return A list of all issues for the specified customer.
	 * @throws CustomerException If there's an issue with the customer or no issues are found.
	 */
	@Override
	public List<Issue> viewAllIssues(Integer customerId) throws CustomerException {
			// Check if the provided customerId is null
		if (customerId == null) {
			throw new CustomerException("CustomerId can't be null");
		}
		// Try to find the customer by their ID in the repository
		Optional<Customer> customer = customerRepository.findById(customerId);
		// If the customer is not found, throw a CustomerException
		if (!customer.isPresent()) {
			throw new CustomerException("Customer is not registered with customerId");
		}
		// Check if the customer's issue list is empty
		if (customer.get().getIssueList().isEmpty()) {
			throw new CustomerException("Empty issue list");
		}
		return customer.get().getIssueList();
	}
		
	/**
	 * Change the password for a customer.
	 *
	 * @param login The login credentials, including the new password.
	 * @return A message indicating the success of the password change.
	 * @throws CustomerException If there's an issue with changing the password.
	 */
	@Override
	public String changePassword(Login login) throws CustomerException {
		
		return null;
	}

	/**
	 * Initiate the password recovery process for a customer.
	 *
	 * @param customerId The ID of the customer for whom password recovery is initiated.
	 * @return A message indicating the success of the password recovery initiation.
	 * @throws CustomerException If there's an issue with the password recovery process.
	 */
	@Override
	public String forgotPassword(Integer customerId) throws CustomerException {
		
		return null;
	}

	/**
	 * Send the password to a customer's email as part of the password recovery process.
	 *
	 * @param customerId The ID of the customer for whom the password is being sent.
	 * @return A message indicating the success of sending the password.
	 * @throws CustomerException If there's an issue with sending the password.
	 */
	@Override
	public String emailPassword(Integer customerId) throws CustomerException {
		
		return null;
	}


	
//	@Override
//	public String logOutFromAccount(String token)throws LoginException {
//		
//		CurrentUserSession validCustomerSession = currentUserSessionRepository.findByToken(token);
//		
//		
//		if(validCustomerSession == null) {
//			throw new LoginException("User Not Logged In with this number");
//			
//		}
//		
//		
//		currentUserSessionRepository.delete(validCustomerSession);
//		
//		
//		return "Logged Out !";
//		
//		
//	}


}

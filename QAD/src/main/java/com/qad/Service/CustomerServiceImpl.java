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

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private CurrentCustomerSessionRepository currentCustomerSessionRepository;


	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		if (customer == null) {
			throw new CustomerException("Customer can't be null");
		}

		return customerRepository.save(customer);
	}
	
	
	
	@Override
	public CurrentCustomerSession loginCustomer(Login login)throws CustomerException, LoginException{
		
		Customer existingCustomer = customerRepository.findByUsername(login.getUsername());
		
		if(existingCustomer == null) {
			throw new LoginException("Please Enter a valid username number");
		}	
		
		Optional<CurrentCustomerSession> validCustomerSessionOpt = currentCustomerSessionRepository.findById(existingCustomer.getCustomerId());
		
		if(validCustomerSessionOpt.isPresent()) {
			throw new LoginException("User already Logged In with this username");
		}
		
		if(existingCustomer.getPassword().equals(login.getPassword())) {
			
			String key= RandomString.make(6);
			
			CurrentCustomerSession currentCustomerSession = new CurrentCustomerSession(existingCustomer.getCustomerId(),key,LocalDateTime.now());
			
			currentCustomerSessionRepository.save(currentCustomerSession);

			return currentCustomerSession;
		}
		else
			throw new LoginException("Please Enter a valid password");
	}
	

	
	@Override
	public Issue viewIssuesById(Integer issueId) throws CustomerException {
		
		if (issueId == null) {
			throw new CustomerException("IssueId can't be null");
		}

		Optional<Issue> issue = issueRepository.findById(issueId);
		
		if(issue.isEmpty())
			throw new CustomerException("Issue doesn't exist with given issueId for the given Customer");
		
		return issue.get();
	}
	

	
	@Override
	public Issue reOpenIssue(Integer issueId) throws CustomerException {
		
	    Issue issue = issueRepository.findById(issueId)
	            .orElseThrow(() -> new CustomerException("Issue not found with ID: " + issueId));

	    if (issue.getStatus() == IssueStatus.RESOLVED) {
	        issue.setStatus(IssueStatus.PENDING);
	       
	        return issueRepository.save(issue);
	    } else {
	        throw new CustomerException("Cannot reopen issue. The issue is not in the RESOLVED state.");
	    }
	}
	

	
	@Override
	public List<Issue> viewAllIssues(Integer customerId) throws CustomerException {
		
		if (customerId == null) {
			throw new CustomerException("CustomerId can't be null");
		}

		Optional<Customer> customer = customerRepository.findById(customerId);
		
		if (!customer.isPresent()) {
		    throw new CustomerException("Customer is not Registered with customerId");
		}

		if(customer.get().getIssueList().isEmpty()) {
    		throw new CustomerException("Empty issue list") ;
    	}
		
		return customer.get().getIssueList();
	}
		
	

	@Override
	public String changePassword(Login login) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public String forgotPassword(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public String emailPassword(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
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

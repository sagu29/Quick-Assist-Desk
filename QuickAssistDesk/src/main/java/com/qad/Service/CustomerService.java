package com.qad.Service;

import java.util.List;

import com.qad.Entity.CurrentCustomerSession;
import com.qad.Entity.Customer;
import com.qad.Entity.Issue;
import com.qad.Entity.IssueStatus;
import com.qad.Entity.Login;
import com.qad.Exception.CustomerException;
import com.qad.Exception.LoginException;

public interface CustomerService {

	public Customer registerCustomer(Customer customer) throws CustomerException;
	
	public CurrentCustomerSession loginCustomer(Login login) throws CustomerException,LoginException;

	public Issue viewIssuesById(Integer issueId) throws CustomerException;

	public Issue reOpenIssue(Integer issueId) throws CustomerException;
	
	public List<Issue> viewAllIssues(Integer customerId) throws CustomerException;
	
	public String changePassword(Login login) throws CustomerException;
	
	public String forgotPassword(Integer customerId) throws CustomerException;
	
	public String emailPassword(Integer customerId) throws CustomerException;
	
}

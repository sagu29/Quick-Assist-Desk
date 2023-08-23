package com.qad.Service;

import java.util.List;

import com.qad.entity.Customer;
import com.qad.entity.Issue;
import com.qad.entity.Login;

public interface CustomerServiceIntr {
 public String login(Login login);
 public String registerCustomer(Customer customer);
 public Issue viewIssueById(int issueId);
 public Issue reponeIssue(int issueId);
 public List<Issue> viewAllIssues();
 public String changePassword(Login login);
 public String forgatePassword(int customerId);
 public Customer emailPassword(int customerId);
	 
 
}

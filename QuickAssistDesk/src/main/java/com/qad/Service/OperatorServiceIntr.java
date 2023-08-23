package com.qad.Service;

import java.util.List;

import com.qad.entity.Customer;
import com.qad.entity.Issue;
import com.qad.entity.Login;

public interface OperatorServiceIntr {
public String login(Login login);
public Issue addCustomerIssue(Issue issue);
public String sendIntimationEmailToCustomer(int a, int b);
public Issue modifyCustomerIssue(Issue issue);
public String sendModificationEmailToCustomer(int a ,int b);
public Issue closeCustomerIssue(Issue issue);
public Customer findCustomerById(int customerId);
public List<Customer> findCustomerByName(String customerName);
public Customer findCustomerByEmail(String customerEmail);
public boolean lockCustomer(int customerId);

}

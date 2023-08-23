package com.qad.Service;

import java.util.List;

import com.qad.entity.Customer;
import com.qad.entity.Issue;
import com.qad.entity.Login;

public class OperatorServiceImpl implements OperatorServiceIntr{

	@Override
	public String login(Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Issue addCustomerIssue(Issue issue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendIntimationEmailToCustomer(int a, int b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Issue modifyCustomerIssue(Issue issue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendModificationEmailToCustomer(int a, int b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Issue closeCustomerIssue(Issue issue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findCustomerByName(String customerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerByEmail(String customerEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean lockCustomer(int customerId) {
		// TODO Auto-generated method stub
		return false;
	}

}

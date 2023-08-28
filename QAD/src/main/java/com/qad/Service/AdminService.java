package com.qad.Service;

import java.util.List;

import com.qad.Entity.CurrentAdminSession;
import com.qad.Entity.Customer;
import com.qad.Entity.Department;
import com.qad.Entity.Login;
import com.qad.Entity.Operator;
import com.qad.Exception.AdminException;
import com.qad.Exception.LoginException;

public interface AdminService {
	
	public CurrentAdminSession loginAdmin(Login login) throws AdminException, LoginException;

	public String addDepartment(Department department) throws AdminException;
	
	public String removeDepartment(Integer departmentId) throws AdminException;
	
	public Department modifyDepartment(Department department, Integer departmentId) throws AdminException;
	
	public Department findDepartmentById(Integer departmentId) throws AdminException;
	
	public String addOperator(Operator operator) throws AdminException;
	
	public String removeOperator(Integer operatorId) throws AdminException;
	
	public Operator modifyOperator(Operator operator, Integer operatorId) throws AdminException;
	
	public Operator findOperatorById(Integer operatorId) throws AdminException;
	
	public List<Operator> findAllOperators() throws AdminException;
	
	public List<Customer> findAllCustomers() throws AdminException;
	public List<Department> findAllDepartement() throws AdminException;
	public String deleteCustomer(int id) throws AdminException;
}

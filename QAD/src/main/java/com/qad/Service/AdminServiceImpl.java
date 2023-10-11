package com.qad.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qad.Entity.Admin;
import com.qad.Entity.CurrentAdminSession;
import com.qad.Entity.CurrentCustomerSession;
import com.qad.Entity.Customer;
import com.qad.Entity.Department;
import com.qad.Entity.Login;
import com.qad.Entity.Operator;
import com.qad.Exception.AdminException;
import com.qad.Exception.LoginException;
import com.qad.Repository.AdminRepository;
import com.qad.Repository.CurrentAdminSessionRepository;
import com.qad.Repository.CustomerRepository;
import com.qad.Repository.DepartmentRepository;
import com.qad.Repository.OperatorRepository;

import net.bytebuddy.utility.RandomString;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private OperatorRepository operatorRepository;
	
	@Autowired
	private CurrentAdminSessionRepository currentAdminSessionRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	/**
	 * Adds a new department to the system.
	 *
	 * @param department The department to be added.
	 * @return A message indicating the success of the operation.
	 * @throws AdminException If the provided department is null.
	 */
	
	
	@Override
	public String addDepartment(Department department) throws AdminException {
		// Check if the provided department is null
		if (department == null) {
			throw new AdminException("Department can't be null");
		}
		
		// Save the department to the repository
		departmentRepository.save(department);

		// Return a success message
		return "Department added successfully";
		
	}
	
	
	/**
	 * Removes a department by its departmentId.
	 *
	 * @param departmentId The ID of the department to be removed.
	 * @return A message indicating the success of the removal.
	 * @throws AdminException If the provided departmentId is null or if the department doesn't exist.
	 */
	@Override
	public String removeDepartment(Integer departmentId) throws AdminException {
		
		if (departmentId == null) {
			throw new AdminException("DepartmentId can't be null");
		}
		
		 // Try to find the department by its ID in the repository
		Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
		
		 // If the department doesn't exist, throw an AdminException
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		// Delete the department with the specified departmentId
		departmentRepository.deleteById(departmentId);
		
		return "Department added successfully";
	}
	
	
	/**
	 * Modifies an existing department with the provided departmentId by updating its departmentName.
	 *
	 * @param department The new department information to be used for the modification.
	 * @param departmentId The ID of the department to be modified.
	 * @return The updated department after modification.
	 * @throws AdminException If the provided department is null or if the department with the specified ID doesn't exist.
	 */
	@Override
	public Department modifyDepartment(Department department, Integer departmentId) throws AdminException {
		// Check if the provided department is null
		if (department == null) {
	        throw new AdminException("Department can't be null");
	    }
		 // Try to find the existing department by its ID in the repository
	    Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
	 
	    // If the department doesn't exist, throw an AdminException
	    if (!existingDepartment.isPresent()) {
	        throw new AdminException("Department doesn't exist with given departmentId");
	    }
	    // Get the existing department to update its departmentName
	    Department departmentToUpdate = existingDepartment.get();
	    departmentToUpdate.setDepartmentName(department.getDepartmentName());
	    
	 // Save the updated department
	    Department updatedDepartment = departmentRepository.save(departmentToUpdate);

	    return updatedDepartment;
	}
	
	
	/**
	 * Find a department in the repository by its departmentId.
	 *
	 * @param departmentId The ID of the department to be retrieved.
	 * @return The department with the specified departmentId.
	 * @throws AdminException If the provided departmentId is null or if the department with the specified ID doesn't exist.
	 */
	
	@Override
	public Department findDepartmentById(Integer departmentId) throws AdminException {
		
		 // Check if the provided departmentId is null
		if (departmentId == null) {
			throw new AdminException("DepartmentId can't be null");
		}
		  // Try to find the department by its ID in the repository
		Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
		
		// If the department doesn't exist, throw an AdminException
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		// Return the found department
		return existingDepartment.get();
	}
	
	
	/**
	 * Adds a new operator to the repository.
	 *
	 * @param operator The operator to be added.
	 * @return A message indicating the success of the addition.
	 * @throws AdminException If the provided operator is null.
	 */
	@Override
	public String addOperator(Operator operator) throws AdminException {
		// Check if the provided operator is null
		if (operator == null) {
			throw new AdminException("Operator can't be null");
		}
		// Save the operator to the repository
		operatorRepository.save(operator);
		
		return "Operator added successfully";
	}
	
	/**
	 * Removes an operator from the repository using the provided operatorId.
	 *
	 * @param operatorId The ID of the operator to be removed.
	 * @return A message indicating the success of the removal.
	 * @throws AdminException If the provided operatorId is null or if the operator with the specified ID doesn't exist.
	 */

	@Override
	public String removeOperator(Integer operatorId) throws AdminException {
		
		// Check if the provided operatorId is null
		if (operatorId == null) {
			throw new AdminException("OperatorId can't be null");
		}
		 // Try to find the operator by its ID in the repository
		Optional<Operator> existingOperator = operatorRepository.findById(operatorId);
		
		 // If the operator doesn't exist, throw an AdminException
		if(existingOperator == null) 
			throw new AdminException("Operator doesn't exist with with given operatorId");
		
		 // Delete the operator with the specified operatorId
		operatorRepository.deleteById(operatorId);
		
		// Return a success message
		return "Operator deleted successfully";
	}
	
	
	/**
	 * Modifies an existing operator in the repository using the provided operatorId.
	 *
	 * @param operator The new operator information to be used for the modification.
	 * @param operatorId The ID of the operator to be modified.
	 * @return The updated operator after modification.
	 * @throws AdminException If the operator with the specified ID doesn't exist.
	 */
	
	@Override
    public Operator modifyOperator(Operator operator, Integer operatorId) throws AdminException {
		// Find the existing operator by its ID in the repository, or throw an exception if not found
        Operator existingOperator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new AdminException("Operator not found"));
        
        // Update the operator's properties with the new information
        existingOperator.setName(operator.getName());
        existingOperator.setEmail(operator.getEmail());
        existingOperator.setUsername(operator.getUsername());
        existingOperator.setPassword(operator.getPassword());
        existingOperator.setMobile(operator.getMobile());
        existingOperator.setCity(operator.getCity());
        existingOperator.setCall(operator.getCall());
        existingOperator.setDepartment(operator.getDepartment());

        // Save the updated operator
        return operatorRepository.save(existingOperator);
	}

	
	/**
	 * Find an operator in the repository by their operatorId.
	 *
	 * @param operatorId The ID of the operator to be retrieved.
	 * @return The operator with the specified operatorId.
	 * @throws AdminException If the provided operatorId is null or if the operator with the specified ID doesn't exist.
	 */
	
	@Override
	public Operator findOperatorById(Integer operatorId) throws AdminException {
		// Check if the provided operatorId is null
		if (operatorId == null) {
			throw new AdminException("OperatorId can't be null");
		}
		// Try to find the operator by its ID in the repository
		Optional<Operator> existingOperator = operatorRepository.findById(operatorId);
		// If the operator doesn't exist, throw an AdminException
		if(existingOperator == null) 
			throw new AdminException("Operator doesn't exist with with given operatorId");
		// Return the found operator
		return existingOperator.get();
	}
	
	
	/**
	 * Retrieve a list of all operators from the repository.
	 *
	 * @return A list of all operators in the repository.
	 * @throws AdminException If no operators are found in the repository.
	 */

	@Override
	public List<Operator> findAllOperators() throws AdminException {
		// Retrieve a list of all operators from the repository
		List<Operator> operators = operatorRepository.findAll();
		// If the list is empty, throw an AdminException
		if (operators.isEmpty()) {
			throw new AdminException("No operators found");
		}
		 // Return the list of operators
		return operators;
	}


	/**
	 * Log in an admin using the provided login credentials.
	 *
	 * @param login The login credentials, including a username and password.
	 * @return The current admin session if the login is successful.
	 * @throws AdminException If an error related to the admin account occurs.
	 * @throws LoginException If the login credentials are invalid, the user is already logged in, or the password is incorrect.
	 */
	@Override
	public CurrentAdminSession loginAdmin(Login login) throws AdminException, LoginException {
		// Find the existing admin by their username
		Admin existingAdmin = adminRepository.findByUsername(login.getUsername());
		
		if(existingAdmin == null) {
			throw new LoginException("Please Enter a valid username number");
		}	
		// Try to find the current admin session for the existing admin
		Optional<CurrentAdminSession> validAdminSessionOpt = currentAdminSessionRepository.findById(existingAdmin.getAdminId());
		 // If a session already exists, throw a LoginException
		if(validAdminSessionOpt.isPresent()) {
			throw new LoginException("User already Logged In with this username");	
		}
		// Check if the password matches the admin's password
		if(existingAdmin.getPassword().equals(login.getPassword())) {
			  // Generate a session key (e.g., a random string)
			String key= RandomString.make(6);
			
			 // Create a new current admin session
			CurrentAdminSession currentAdminSession = new CurrentAdminSession(existingAdmin.getAdminId(),key,LocalDateTime.now());
			
			// Save the current admin session
			currentAdminSessionRepository.save(currentAdminSession);
			
			// Return the current admin session
			return currentAdminSession;
		}
		else
			throw new LoginException("Please Enter a valid password");
	}


	
	/**
	 * Retrieve a list of all customers from the repository.
	 *
	 * @return A list of all customers in the repository.
	 * @throws AdminException If no customers are found in the repository.
	 */
	@Override
	public List<Customer> findAllCustomers() throws AdminException {
		// Retrieve a list of all customers from the repository
		List<Customer> customers=customerRepository.findAll();
		// If the list is empty, throw an AdminException
		if (customers.isEmpty()) {
			throw new AdminException("No Customers found");
		}
		// Return the list of customers
		return customers;
	}


	/**
	 * Retrieve a list of all departments from the repository.
	 *
	 * @return A list of all departments in the repository.
	 * @throws AdminException If there's an issue retrieving the list of departments.
	 */
	@Override
	public List<Department> findAllDepartement() throws AdminException {
		// TODO Auto-generated method stub
		// Retrieve a list of all departments from the repository
		List<Department> dlist=departmentRepository.findAll();
		
		// Return the list of departments
		return dlist;
	}



	/**
	 * Delete a customer by their ID.
	 *
	 * @param id The ID of the customer to be deleted.
	 * @return A message indicating the success of the deletion or if the customer is not found.
	 * @throws AdminException If there's an issue with the deletion process.
	 */
	@Override
	public String deleteCustomer(int id) throws AdminException {
	    // Try to find the customer by their ID in the repository
	    Optional<Customer> customer = customerRepository.findById(id);
	    
	    // If a customer with the specified ID is found, delete it
	    if (customer.isPresent()) {
	        customerRepository.deleteById(id);
	        return "Deleted successfully";
	    }

	    // If the customer is not found, return a message indicating that
	    return "Not found";
	}

	

}

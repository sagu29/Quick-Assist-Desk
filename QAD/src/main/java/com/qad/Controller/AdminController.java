package com.qad.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qad.Entity.CurrentAdminSession;
import com.qad.Entity.Customer;
import com.qad.Entity.Department;
import com.qad.Entity.Login;
import com.qad.Entity.Operator;
import com.qad.Exception.AdminException;
import com.qad.Exception.LoginException;
import com.qad.Service.AdminService;

import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/adminLogin")
	public ResponseEntity<CurrentAdminSession> logInAdmin(@RequestBody Login login) throws LoginException, AdminException {
		
		CurrentAdminSession result = adminService.loginAdmin(login);
			
		return new ResponseEntity<CurrentAdminSession>(result,HttpStatus.OK );
	}

	
	
	@PostMapping("/addDepartment")
	public ResponseEntity<String> addDepartmentHandler(@RequestBody @Valid Department department) throws AdminException{
		
		String s = adminService.addDepartment(department);
		
		return new ResponseEntity<String>(s,HttpStatus.CREATED);
	}
	
	
	
	@DeleteMapping("/deleteDepartment/{departmentId}")
	private ResponseEntity<String> deleteDepartmentHandler(@PathVariable Integer departmentId) throws AdminException {
		
		String s = adminService.removeDepartment(departmentId);
		
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/modifyDepartment/{departmentId}")
	public ResponseEntity<Department> modifyDepartmentHandler(@RequestBody @Valid Department department, @PathVariable Integer departmentId)throws AdminException {
		
		Department dept = adminService.modifyDepartment(department, departmentId);
		
		return new ResponseEntity<Department>(dept,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<Department> getDepartmentByIdHandler(@PathVariable Integer departmentId)throws AdminException {
		
		Department dept = adminService.findDepartmentById(departmentId);
		
		return new ResponseEntity<Department>(dept,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/addOperator")
	public ResponseEntity<String> addOperatorHandler(@RequestBody @Valid Operator operator) throws AdminException{
		
		String s = adminService.addOperator(operator);
		
		return new ResponseEntity<String>(s,HttpStatus.CREATED);
	}
	
	
	
	@DeleteMapping("/deleteOperator/{operatorId}")
	private ResponseEntity<String> deleteOperatorHandler(@PathVariable Integer operatorId) throws AdminException {
		
		String s = adminService.removeOperator(operatorId);
		
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/modifyOperator/{operatorId}")
	public ResponseEntity<Operator> modifyOperatorHandler(@RequestBody @Valid Operator operator, @PathVariable Integer operatorId)throws AdminException {
		
		Operator opt = adminService.modifyOperator(operator, operatorId);
		
		return new ResponseEntity<Operator>(opt,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/operator/{operatorId}")
	public ResponseEntity<Operator> getOperatorByIdHandler(@PathVariable Integer operatorId)throws AdminException {
		
		Operator opt = adminService.findOperatorById(operatorId);
		
		return new ResponseEntity<Operator>(opt,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCustomer/{customerId}")
	private ResponseEntity<String> deleteCustomerById(@PathVariable Integer customerId) throws AdminException {
		
		String s = adminService.deleteCustomer(customerId);
		
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	
	@GetMapping("/allOperators")
	public ResponseEntity<List<Operator>> getAllOperatorHandler()throws AdminException{
		    
		return new ResponseEntity<List<Operator>>(adminService.findAllOperators(), HttpStatus.OK);
	}
	@GetMapping("/allCustomer")
	public ResponseEntity<List<Customer>> findAllCustomers() throws AdminException{
		return new ResponseEntity<List<Customer>>(adminService.findAllCustomers(), HttpStatus.OK);
	}
	@GetMapping("/AllDepartement")
	public ResponseEntity<List<Department>> findAllDepartement() throws AdminException{
		return new  ResponseEntity<List<Department>> (adminService.findAllDepartement(),HttpStatus.OK);
		
	}
}

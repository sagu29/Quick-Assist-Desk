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

/**
 * Controller class for handling admin-related operations.
 */
@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Log in an admin using the provided login credentials.
     *
     * @param login The login credentials, including a username and password.
     * @return The current admin session if the login is successful.
     * @throws LoginException If there's an issue related to the admin account.
     * @throws AdminException If there's an issue with the login process.
     */
    @PostMapping("/adminLogin")
    public ResponseEntity<CurrentAdminSession> logInAdmin(@RequestBody Login login) throws LoginException, AdminException {

        CurrentAdminSession result = adminService.loginAdmin(login);

        return new ResponseEntity<CurrentAdminSession>(result, HttpStatus.OK);
    }

    /**
     * Add a department to the system.
     *
     * @param department The department to be added.
     * @return A message indicating the success of adding the department.
     * @throws AdminException If there's an issue with adding the department.
     */
    @PostMapping("/addDepartment")
    public ResponseEntity<String> addDepartmentHandler(@RequestBody @Valid Department department) throws AdminException {

        String s = adminService.addDepartment(department);

        return new ResponseEntity<String>(s, HttpStatus.CREATED);
    }

    /**
     * Delete a department from the system by its ID.
     *
     * @param departmentId The ID of the department to be deleted.
     * @return A message indicating the success of deleting the department.
     * @throws AdminException If there's an issue with deleting the department.
     */
    @DeleteMapping("/deleteDepartment/{departmentId}")
    private ResponseEntity<String> deleteDepartmentHandler(@PathVariable Integer departmentId) throws AdminException {

        String s = adminService.removeDepartment(departmentId);

        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

    /**
     * Modify a department based on the provided department details and department ID.
     *
     * @param department The updated department.
     * @param departmentId The ID of the department to be modified.
     * @return The modified department.
     * @throws AdminException If there's an issue with modifying the department.
     */
    @PutMapping("/modifyDepartment/{departmentId}")
    public ResponseEntity<Department> modifyDepartmentHandler(@RequestBody @Valid Department department, @PathVariable Integer departmentId) throws AdminException {

        Department dept = adminService.modifyDepartment(department, departmentId);

        return new ResponseEntity<Department>(dept, HttpStatus.CREATED);
    }

    /**
     * Get a department by its ID.
     *
     * @param departmentId The ID of the department to be retrieved.
     * @return The department with the specified ID.
     * @throws AdminException If there's an issue with finding the department.
     */
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Department> getDepartmentByIdHandler(@PathVariable Integer departmentId) throws AdminException {

        Department dept = adminService.findDepartmentById(departmentId);

        return new ResponseEntity<Department>(dept, HttpStatus.OK);
    }

    /**
     * Add an operator to the system.
     *
     * @param operator The operator to be added.
     * @return A message indicating the success of adding the operator.
     * @throws AdminException If there's an issue with adding the operator.
     */
    @PostMapping("/addOperator")
    public ResponseEntity<String> addOperatorHandler(@RequestBody @Valid Operator operator) throws AdminException {

        String s = adminService.addOperator(operator);

        return new ResponseEntity<String>(s, HttpStatus.CREATED);
    }

    /**
     * Delete an operator from the system by their ID.
     *
     * @param operatorId The ID of the operator to be deleted.
     * @return A message indicating the success of deleting the operator.
     * @throws AdminException If there's an issue with deleting the operator.
     */
    @DeleteMapping("/deleteOperator/{operatorId}")
    private ResponseEntity<String> deleteOperatorHandler(@PathVariable Integer operatorId) throws AdminException {

        String s = adminService.removeOperator(operatorId);

        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

    /**
     * Modify an operator based on the provided operator details and operator ID.
     *
     * @param operator The updated operator.
     * @param operatorId The ID of the operator to be modified.
     * @return The modified operator.
     * @throws AdminException If there's an issue with modifying the operator.
     */
    @PutMapping("/modifyOperator/{operatorId}")
    public ResponseEntity<Operator> modifyOperatorHandler(@RequestBody @Valid Operator operator, @PathVariable Integer operatorId) throws AdminException {

        Operator opt = adminService.modifyOperator(operator, operatorId);

        return new ResponseEntity<Operator>(opt, HttpStatus.OK);
    }

    /**
     * Get an operator by their ID.
     *
     * @param operatorId The ID of the operator to be retrieved.
     * @return The operator with the specified ID.
     * @throws AdminException If there's an issue with finding the operator.
     */
    @GetMapping("/operator/{operatorId}")
    public ResponseEntity<Operator> getOperatorByIdHandler(@PathVariable Integer operatorId) throws AdminException {

        Operator opt = adminService.findOperatorById(operatorId);

        return new ResponseEntity<Operator>(opt, HttpStatus.OK);
    }

    /**
     * Delete a customer from the system by their ID.
     *
     * @param customerId The ID of the customer to be deleted.
     * @return A message indicating the success of deleting the customer.
     * @throws AdminException If there's an issue with deleting the customer.
     */
    @DeleteMapping("/deleteCustomer/{customerId}")
    private ResponseEntity<String> deleteCustomerById(@PathVariable Integer customerId) throws AdminException {

        String s = adminService.deleteCustomer(customerId);

        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

    /**
     * Get a list of all operators in the system.
     *
     * @return A list of all operators in the system.
     * @throws AdminException If there's an issue with retrieving the operators.
     */
    @GetMapping("/allOperators")
    public ResponseEntity<List<Operator>> getAllOperatorHandler() throws AdminException {
        return new ResponseEntity<List<Operator>>(adminService.findAllOperators(), HttpStatus.OK);
    }

    /**
     * Get a list of all customers in the system.
     *
     * @return A list of all customers in the system.
     * @throws AdminException If there's an issue with retrieving the customers.
     */
    @GetMapping("/allCustomer")
    public ResponseEntity<List<Customer>> findAllCustomers() throws AdminException {
        return new ResponseEntity<List<Customer>>(adminService.findAllCustomers(), HttpStatus.OK);
    }

    /**
     * Get a list of all departments in the system.
     *
     * @return A list of all departments in the system.
     * @throws AdminException If there's an issue with retrieving the departments.
     */
    @GetMapping("/AllDepartement")
    public ResponseEntity<List<Department>> findAllDepartement() throws AdminException {
        return new ResponseEntity<List<Department>>(adminService.findAllDepartement(), HttpStatus.OK);
    }
}


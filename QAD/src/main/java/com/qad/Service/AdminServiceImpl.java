package com.qad.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qad.Entity.Admin;
import com.qad.Entity.CurrentAdminSession;
import com.qad.Entity.CurrentCustomerSession;
import com.qad.Entity.Department;
import com.qad.Entity.Login;
import com.qad.Entity.Operator;
import com.qad.Exception.AdminException;
import com.qad.Exception.LoginException;
import com.qad.Repository.AdminRepository;
import com.qad.Repository.CurrentAdminSessionRepository;
import com.qad.Repository.DepartmentRepository;
import com.qad.Repository.OperatorRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServiceImpl implements AdminService {

	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private OperatorRepository operatorRepository;
	
	@Autowired
	private CurrentAdminSessionRepository currentAdminSessionRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	
	@Override
	public String addDepartment(Department department) throws AdminException {
		
		if (department == null) {
			throw new AdminException("Department can't be null");
		}
	
		departmentRepository.save(department);

		
		return "Department added successfully";
		
	}
	
	

	@Override
	public String removeDepartment(Integer departmentId) throws AdminException {
		
		if (departmentId == null) {
			throw new AdminException("DepartmentId can't be null");
		}

		Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
		
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		departmentRepository.deleteById(departmentId);
		
		return "Department added successfully";
	}
	
	

	@Override
	public Department modifyDepartment(Department department, Integer departmentId) throws AdminException {
	    if (department == null) {
	        throw new AdminException("Department can't be null");
	    }

	    Optional<Department> existingDepartment = departmentRepository.findById(departmentId);

	    if (!existingDepartment.isPresent()) {
	        throw new AdminException("Department doesn't exist with given departmentId");
	    }

	    Department departmentToUpdate = existingDepartment.get();
	    departmentToUpdate.setDepartmentName(department.getDepartmentName());

	    Department updatedDepartment = departmentRepository.save(departmentToUpdate);

	    return updatedDepartment;
	}
	
	

	@Override
	public Department findDepartmentById(Integer departmentId) throws AdminException {
		
		if (departmentId == null) {
			throw new AdminException("DepartmentId can't be null");
		}

		Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
		
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		return existingDepartment.get();
	}
	
	

	@Override
	public String addOperator(Operator operator) throws AdminException {
		
		if (operator == null) {
			throw new AdminException("Operator can't be null");
		}

		operatorRepository.save(operator);
		
		return "Operator added successfully";
	}
	
	

	@Override
	public String removeOperator(Integer operatorId) throws AdminException {
		
		if (operatorId == null) {
			throw new AdminException("OperatorId can't be null");
		}
		
		Optional<Operator> existingOperator = operatorRepository.findById(operatorId);
		
		if(existingOperator == null) 
			throw new AdminException("Operator doesn't exist with with given operatorId");
		
		operatorRepository.deleteById(operatorId);
		
		return "Operator deleted successfully";
	}
	
	

	@Override
    public Operator modifyOperator(Operator operator, Integer operatorId) throws AdminException {
		
        Operator existingOperator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new AdminException("Operator not found"));

        existingOperator.setName(operator.getName());
        existingOperator.setEmail(operator.getEmail());
        existingOperator.setUsername(operator.getUsername());
        existingOperator.setPassword(operator.getPassword());
        existingOperator.setMobile(operator.getMobile());
        existingOperator.setCity(operator.getCity());
        existingOperator.setCall(operator.getCall());
        existingOperator.setDepartment(operator.getDepartment());

        
        return operatorRepository.save(existingOperator);
	}

	
	
	@Override
	public Operator findOperatorById(Integer operatorId) throws AdminException {
		
		if (operatorId == null) {
			throw new AdminException("OperatorId can't be null");
		}
		
		Optional<Operator> existingOperator = operatorRepository.findById(operatorId);
		
		if(existingOperator == null) 
			throw new AdminException("Operator doesn't exist with with given operatorId");
		
		return existingOperator.get();
	}
	
	

	@Override
	public List<Operator> findAllOperators() throws AdminException {

		List<Operator> operators = operatorRepository.findAll();
		
		if (operators.isEmpty()) {
			throw new AdminException("No operators found");
		}
		
		return operators;
	}



	@Override
	public CurrentAdminSession loginAdmin(Login login) throws AdminException, LoginException {

		Admin existingAdmin = adminRepository.findByUsername(login.getUsername());
		
		if(existingAdmin == null) {
			throw new LoginException("Please Enter a valid username number");
		}	
		
		Optional<CurrentAdminSession> validAdminSessionOpt = currentAdminSessionRepository.findById(existingAdmin.getAdminId());
		
		if(validAdminSessionOpt.isPresent()) {
			throw new LoginException("User already Logged In with this username");	
		}
		
		if(existingAdmin.getPassword().equals(login.getPassword())) {
			
			String key= RandomString.make(6);
			
			CurrentAdminSession currentAdminSession = new CurrentAdminSession(existingAdmin.getAdminId(),key,LocalDateTime.now());
			
			currentAdminSessionRepository.save(currentAdminSession);

			return currentAdminSession;
		}
		else
			throw new LoginException("Please Enter a valid password");
	}
	

}

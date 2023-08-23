package com.qad.Service;

import java.util.List;

import com.qad.entity.Department;
import com.qad.entity.Operator;

public interface AdminServiceIntr {
  public boolean addDepartment(Department department);
  public boolean removeDepartment(int departmentId);
  public Department modifyDepartment(Department department);
  public Department findDepartmenById(int departmentId);
  public boolean    addOperator(Operator operator);
  public boolean removeOperator(Operator operator);
  public Operator modifyOperator(Operator operator);
  public Operator findOperatorById(int operatorId);
  public List<Operator> findAllOperators();
}

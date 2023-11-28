package com.pavan.dao;
import com.pavan.dto.*;
import java.util.*;
public interface EmployeeDao {
	public String add(Employee emp);
	public Employee search(int eno);
	public List<Employee> getAllEmployees();
	public String update(Employee emp);
	public String delete(int eno);
}

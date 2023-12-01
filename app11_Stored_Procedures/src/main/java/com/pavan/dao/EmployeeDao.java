package com.pavan.dao;
import com.pavan.dto.*;

public interface EmployeeDao {
	public void create(Employee emp);
	public float getEmpSal(int eno);
	
	public void updateEmployee(Employee emp);
	public void deleteEmployee(int eno);
}

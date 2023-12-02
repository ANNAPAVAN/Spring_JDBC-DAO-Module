package com.pavan.dao;
import com.pavan.dto.*;

public interface EmployeeDao {
	public void insertEmployee(Employee emp);
	public Employee readEmployee(int eno);
}

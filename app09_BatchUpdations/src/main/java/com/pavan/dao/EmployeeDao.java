package com.pavan.dao;
import com.pavan.dto.*;
import java.util.*;
public interface EmployeeDao {
	public int[] insert(List<Employee> empList);
	public int[] update();
}

package com.pavan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pavan.dto.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {
	public Employee mapRow(ResultSet rs,int arg1) throws SQLException{
		Employee emp = new Employee();
		emp.setEno(rs.getInt("eno"));
		emp.setEname(rs.getString("ename"));
		emp.setEsal(rs.getFloat("esal"));
		emp.setEaddr(rs.getString("eaddr"));
		return emp;
	}
}

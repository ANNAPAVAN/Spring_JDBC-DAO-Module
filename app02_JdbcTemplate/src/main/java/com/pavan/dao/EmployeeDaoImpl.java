package com.pavan.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.pavan.dto.Employee;
import com.pavan.mapper.*;
public class EmployeeDaoImpl implements EmployeeDao {
	
	private JdbcTemplate JdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		JdbcTemplate = jdbcTemplate;
	}
	
	
	public String add(Employee emp) {
		String status = "";
		try {
			List<Employee> empList = JdbcTemplate.query("select * from employee where eno ="+emp.getEno(), new EmployeeRowMapper());
			if(empList.isEmpty())
			{
				int rowCount= JdbcTemplate.update("insert into employee values("+emp.getEno()+",'"+emp.getEname()+"',"+emp.getEsal()+",'"+emp.getEaddr()+"')");
				if(rowCount==1)
				{
					status="Employee Inserted Successfully";
				}
				else
				{
					status="Employee Insertion Failure";
				}
			}else {
				status = "Employee Existed Already";
			}
		}catch(Exception e)
		{
			status="Employee Insertion Failure";
			e.printStackTrace();
		}
		
		return status;
	}

	public Employee search(int eno) {
		Employee emp = null;
		try {
			List<Employee> empList = JdbcTemplate.query("select * from Employee where eno="+eno,new EmployeeRowMapper() );
			if(empList.isEmpty())
			{
				emp = null;
			}else {
				emp = empList.get(0);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return emp;
	}

	public String update(Employee emp) {
		String status="";
		try {
			
				int rowCount = JdbcTemplate.update("update Employee set ename = '"+emp.getEname()+"',Esal = "+emp.getEsal()+", Eaddr = '"+emp.getEaddr()+"' where eno = "+emp.getEno());
				if(rowCount==1)
				{
					status="Employee Updated Successfully";
				}
				else
				{
					status = "Employee Updation Failure";
				}
			
		}catch(Exception e)
		{
			status = "Employee Updation Failure";
			e.printStackTrace();
		}
		return status;
	}

	public String delete(int eno) {
		String status="";
		try {
			Employee emp = search(eno);
			if(emp==null)
			{
				status = "Employee Not Existed";
			}else {
				int rowCount = JdbcTemplate.update("delete from Employee where eno ="+eno);
				if(rowCount==1)
				{
					status = "Employee Deleted Successfully";
				}else {
					status = "Employee Deletion Failure";
				}
			}
		}catch(Exception e)
		{
			status = "Employee Deletion Failure";
			e.printStackTrace();
		}
		return status;
	}

}

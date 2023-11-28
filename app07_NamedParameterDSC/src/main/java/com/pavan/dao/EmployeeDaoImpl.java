package com.pavan.dao;
import com.pavan.mapper.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.pavan.dto.Employee;

public class EmployeeDaoImpl extends NamedParameterJdbcDaoSupport implements EmployeeDao {

	private DataSource ds; //property name should not be dataSource , due to already exists in NamedParameterJdbcDaoSupport
	public void setDs(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public String add(Employee emp) {
		String status="";
		try {
			Employee emp1 = search(emp.getEno());
			if(emp1==null)
			{
				String query = "insert into Employee values(:eno,:ename,:esal,:eaddr)";
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("eno", emp.getEno());
				params.put("ename", emp.getEname());
				params.put("esal", emp.getEsal());
				params.put("eaddr", emp.getEaddr());
				int rowCount = getNamedParameterJdbcTemplate().update(query,params);
				if(rowCount==1)
				{
					status="Employee Inserted Successfully";
				}else {
					status="Employee Insertion Failure";
				}
			}else {
				status="Employee Already Existed";
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
			String query = "select * from Employee where eno = :eno";
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("eno", eno);
			List<Employee> empsList = getNamedParameterJdbcTemplate().query(query,params,new EmployeeRowMapper());
			if(empsList.isEmpty())
			{
				emp=null;
			}else {
				emp = empsList.get(0);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return emp;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> empsList=null;
		try {
			empsList = getNamedParameterJdbcTemplate().query("select * from Employee",new EmployeeRowMapper());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return empsList;
	}

	public String update(Employee emp) {
		String status="";
		try {
			Employee emp1 = search(emp.getEno());
			if(emp1==null)
			{
				status = "Employee Not Existed";
			}else {
				String query="update Employee set ename= :ename, esal= :esal, eaddr= :eaddr where eno= :eno";
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("ename", emp.getEname());
				params.put("esal", emp.getEsal());
				params.put("eaddr", emp.getEaddr());
				params.put("eno", emp.getEno());
				int rowCount = getNamedParameterJdbcTemplate().update(query,params);
				if(rowCount==1)
				{
					status = "Employee Updation Success";
				}else {
					status="Employee Updataion Failure";
				}
			}
		}catch(Exception e)
		{
			status="Employee Updataion Failure";
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
				String query="delete from Employee where eno= :eno";
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("eno", eno);
				int rowCount = getNamedParameterJdbcTemplate().update(query,params);
				if(rowCount==1)
				{
					status = "Employee Deletion Success";
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

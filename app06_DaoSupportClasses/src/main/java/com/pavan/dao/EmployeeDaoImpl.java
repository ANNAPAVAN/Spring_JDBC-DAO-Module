package com.pavan.dao;
import com.pavan.mapper.*;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.pavan.dto.Employee;

public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	public String add(Employee emp) {
		String status="";
		try {
			Employee emp1 = search(emp.getEno());
			if(emp1==null)
			{
				String query = "insert into Employee values(?,?,?,?)";
				int rowCount = getJdbcTemplate().update(query,new Object[] {emp.getEno(),emp.getEname(),emp.getEsal(),emp.getEaddr()});
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
			String query = "select * from Employee where eno = ?";
			List<Employee> empsList = getJdbcTemplate().query(query,new Object[] {eno},new EmployeeRowMapper());
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
			empsList = getJdbcTemplate().query("select * from Employee",new EmployeeRowMapper());
			
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
				String query="update Employee set ename=?, esal=?, eaddr=? where eno=?";
				int rowCount = getJdbcTemplate().update(query, new Object[] {emp.getEname(),emp.getEsal(),emp.getEaddr(),emp.getEno()});
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
				String query="delete from Employee where eno=?";
				int rowCount = getJdbcTemplate().update(query,new Object[] {eno});
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

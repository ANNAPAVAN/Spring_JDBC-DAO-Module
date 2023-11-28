package com.pavan.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pavan.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int[] insert(final List<Employee> empList) {
		int[] rowCounts = null;
		try {
			String query = "insert into Employee values(?,?,?,?)";
			rowCounts = jdbcTemplate.batchUpdate(query,new BatchPreparedStatementSetter() {
				
				public void setValues(PreparedStatement ps, int index) throws SQLException {
					
					Employee emp = empList.get(index);
					ps.setInt(1, emp.getEno());
					ps.setString(2, emp.getEname());
					ps.setFloat(3, emp.getEsal());
					ps.setString(4, emp.getEaddr());
				}
				public int getBatchSize() {					
					return empList.size();
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowCounts;
	}
	public int[] update()
	{
		int[] rowCounts = null;
		try {
			String query1 = "insert into Employee values(555,'EEE',11000,'Hyd')";
			String query2 = "update Employee set esal = esal+500 where esal<30000";
			String query3 = "delete from Employee where eno=111";
			
			rowCounts = jdbcTemplate.batchUpdate(query1,query2,query3);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return rowCounts;
	}

}


/*
//  ---------------- for inserting ----------
 	DELIMITER //

	CREATE PROCEDURE insertEmployee(IN emp_no INT, IN emp_name VARCHAR(255), IN emp_sal FLOAT, IN emp_addr VARCHAR(255))
	BEGIN
	    INSERT INTO Employee VALUES (emp_no, emp_name, emp_sal, emp_addr);
	END //
	
	DELIMITER ;
	
//	------------- for retriving -------------
	DELIMITER //
	
	CREATE PROCEDURE getSal(IN eno_param INT, OUT sal_param FLOAT)
	BEGIN
	    SELECT esal INTO sal_param FROM employee WHERE eno = eno_param;
	END //
	
	DELIMITER ;
	
//	------------- for updating Employee -------------
 
    DELIMITER //
	
	CREATE PROCEDURE updateEmployee(
	    IN emp_no INT,
	    IN emp_name VARCHAR(255),
	    IN emp_sal FLOAT,
	    IN emp_addr VARCHAR(255)
	)
	BEGIN
	    UPDATE Employee
	    SET ename = emp_name,
	        esal = emp_sal,
	        eaddr = emp_addr
	    WHERE eno = emp_no;
	END //
	
	DELIMITER ;
	
//-------- delete Employee--------------
    DELIMITER //

	CREATE PROCEDURE deleteEmployee(IN emp_no INT)
	BEGIN
	    DELETE FROM Employee WHERE eno = emp_no;
	END //
	
	DELIMITER ;

 */




package com.pavan.dao;

import com.pavan.dto.Employee;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import java.util.*;
public class EmployeeDaoImpl implements EmployeeDao {
	private DataSource dataSource;
	private SimpleJdbcCall simpleJdbcCall;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		simpleJdbcCall = new SimpleJdbcCall(dataSource);
		
	}

	public void create(Employee emp) {
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("insertEmployee");
//			SqlParameterSource params = new BeanPropertySqlParameterSource(emp);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("emp_no",emp.getEno());
			params.put("emp_name", emp.getEname());
			params.put("emp_sal", emp.getEsal());
			params.put("emp_addr", emp.getEaddr());
			simpleJdbcCall.execute(params);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public float getEmpSal(int eno) {
		float sal=0.0f;
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("getSal");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("eno_param", eno);
			Map<String,Object> outValues = simpleJdbcCall.execute(params);
			sal = (Float)outValues.get("sal_param");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sal;
	}
	
	

	
	public void updateEmployee(Employee emp)
	{
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("updateEmployee");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("emp_no",emp.getEno());
			params.put("emp_name", emp.getEname());
			params.put("emp_sal", emp.getEsal());
			params.put("emp_addr", emp.getEaddr());
			simpleJdbcCall.execute(params);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEmployee(int eno)
	{
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("deleteEmployee");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("emp_no", eno);
			simpleJdbcCall.execute(params);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

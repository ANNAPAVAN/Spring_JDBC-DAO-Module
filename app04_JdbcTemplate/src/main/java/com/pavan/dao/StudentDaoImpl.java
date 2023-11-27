package com.pavan.dao;
import java.util.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.pavan.mapper.*;
import com.pavan.dto.Student;

public class StudentDaoImpl implements StudentDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public String add(Student std) {
		String status="";
		String query="";
		try {
			query = "select * from studentApp where sid= :sid";
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("sid", std.getSid());
			List<Student> studentList = namedParameterJdbcTemplate.query(query, params,new StudentRowMapper());
			if(studentList.isEmpty())
			{
				query = "insert into StudentApp values(:sid,:sname,:saddr)";
				params = new HashMap<String,Object>();
				params.put("sid", std.getSid());
				params.put("sname", std.getSname());
				params.put("saddr", std.getSaddr());
				int rowCount = namedParameterJdbcTemplate.update(query,params);
				if(rowCount==1)
				{
					status = "Student Inserted Successfully";
				}
				else {
					status = "Student Insertion Failure";
				}
				
			}else{
				status = "Student Existed Already";
			}
		}catch(Exception e)
		{
			status = "Student Insertion Failure";
			e.printStackTrace();
		}
		return status;
	}

	public Student search(String sid) {
		Student std = null;
		try {
			String query = "select * from studentApp where sid= :sid";
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("sid", sid);
			List<Student> studentList = namedParameterJdbcTemplate.query(query,params,new StudentRowMapper());
			if(studentList.isEmpty())
			{
				std=null;
			}else {
				std = studentList.get(0);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return std;
	}

	public String update(Student std) {
		String status="";
		try {
			
				String query = "update StudentApp set sname= :sname, saddr= :saddr where sid = :sid";
				Map<String,Object> params= new HashMap<String,Object>();
				params.put("sid", std.getSid());
				params.put("sname", std.getSname());
				params.put("saddr", std.getSaddr());
				int rowCount = namedParameterJdbcTemplate.update(query, params);
				if(rowCount==1)
				{
					status = "Student updated Successfully";
				}
				else
				{
					status = "Student updation Failure";
				}
			
		}catch(Exception e)
		{
			status = "Student updation Failure";
			e.printStackTrace();
		}
		return status;
	}

	public String delete(String sid) {
		String status="";
		try {
			Student std = search(sid);
			if(std==null)
			{
				status="Student Not Existed";
			}
			else
			{
				String query="delete from studentApp where sid= :sid";
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("sid", sid);
				int rowCount = namedParameterJdbcTemplate.update(query, params);
				if(rowCount==1)
				{
					status = "Student Deletion Success";
				}else {
					status = "Student Deletion Failure";
				}
			}
		}catch(Exception e)
		{
			status = "Student Deletion Failure";
			e.printStackTrace();
		}
		return status;
	}

}

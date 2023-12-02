package com.pavan.dao;

import com.pavan.dto.Employee;
import java.io.*;
import java.sql.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.FileCopyUtils;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;

import java.sql.SQLException;
import org.springframework.dao.DataAccessException;



public class EmployeeDaoImpl implements EmployeeDao {
	private JdbcTemplate jdbcTemplate;
	private LobHandler lobHandler;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	public LobHandler getLobHandler() {
		return lobHandler;
	}
	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}

	public void insertEmployee(final Employee emp) {
		String query = "insert into emp20 values(?,?,?,?)";
		jdbcTemplate.execute(query,new AbstractLobCreatingPreparedStatementCallback(lobHandler){
			protected void setValues(PreparedStatement pst,LobCreator lobCreator) throws SQLException,DataAccessException{
				try {
					pst.setInt(1,emp.getEno());
					pst.setString(2,emp.getEname());
					FileInputStream fis = new FileInputStream(emp.getEmp_image());
					FileReader fr = new FileReader(emp.getEmp_resume());
					lobCreator.setBlobAsBinaryStream(pst, 3, fis, (int)emp.getEmp_image().length());
					lobCreator.setClobAsCharacterStream(pst, 4, fr, (int)emp.getEmp_resume().length());
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Employee readEmployee(int eno) {
		final Employee emp = new Employee();
		String query = "select * from emp20 where eno = "+eno;
		jdbcTemplate.query(query,new AbstractLobStreamingResultSetExtractor<Object>() {
			protected void streamData(ResultSet rs) throws SQLException,IOException,DataAccessException{
				emp.setEno(rs.getInt("eno"));
				emp.setEname(rs.getString("ename"));
				
				File file1 = new File("E:/trash_docs/new_my_cat.jpeg");
				FileOutputStream fos = new FileOutputStream(file1);
				FileCopyUtils.copy(lobHandler.getBlobAsBinaryStream(rs, 3), fos);
				emp.setEmp_image(file1);
				
				File file2 = new File("E:/trash_docs/my_Resume.docx");
				FileWriter fw = new FileWriter(file2);
				FileCopyUtils.copy(lobHandler.getClobAsCharacterStream(rs, 4),fw);
				emp.setEmp_resume(file2);
				
			}
		});
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

}

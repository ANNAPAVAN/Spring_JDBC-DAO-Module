package com.pavan.jdbc;

import java.sql.*;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionPoolingEx1 {
	
	public static void main(String[] args) {
		MysqlDataSource ds=null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			ds = new MysqlDataSource();
			ds.setUrl("jdbc:mysql://localhost:3306/db");
			ds.setUser("root");
			ds.setPassword("Mysql134");
			
			con = ds.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from Employee");
			System.out.println("Eno\tEname\tEsal\tEaddr");
			System.out.println("-------------------------");
			while(rs.next())
			{
				System.out.println(rs.getInt("eno")+"\t"+rs.getString("ename")+"\t"+rs.getFloat("esal")+"\t"+rs.getString("eaddr"));     
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				con.close();//sending Connection object to pool , not destroying
//				ds.close();  --->deprecated
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}

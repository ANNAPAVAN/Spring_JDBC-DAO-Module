package com.pavan.jdbc;
import java.sql.*;
import java.io.*;
public class Clob_Read_Ex {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		FileWriter fw = null;
		Reader r = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","Mysql134");
			pst = con.prepareStatement("select * from apps where app_name=?");
			pst.setString(1, "app1");
			rs = pst.executeQuery();
			rs.next();
			System.out.println("Application name : "+rs.getString(1));
			r = rs.getCharacterStream(2);
			fw = new FileWriter("E:/trash_docs/from_db_applicationContext.xml");
			int val = r.read();
			while(val!=-1) {
				fw.write(val);
				val = r.read();
			}
			System.out.println("Clob data retrived from db to E:/trash_docs/from_db_applicationContext.xml");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				r.close();
				fw.close();
				rs.close();
				pst.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}

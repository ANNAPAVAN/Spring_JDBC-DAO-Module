package com.pavan.jdbc;
import java.sql.*;
import java.io.*;
public class Blob_Read_Ex {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","Mysql134");
			pst = con.prepareStatement("select * from emp10 where eno = ?");
			pst.setInt(1, 111);
			rs = pst.executeQuery();
			rs.next();
			System.out.println("Employee Number : "+rs.getInt("eno"));
			fos = new FileOutputStream("C:\\Users\\pavan\\OneDrive\\Desktop\\new-cat.jpg");
			is = rs.getBinaryStream(2);
			 
			int val = is.read();
			while(val!=-1)
			{
				fos.write(val);
				val = is.read();
			}
			System.out.println("Image data retrived from db to C:\\Users\\pavan\\OneDrive\\Desktop\\new_cat.jpg");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
				fos.close();
				rs.close();
				pst.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		

	}

}

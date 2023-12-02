package com.pavan.jdbc;
import java.sql.*;
import java.io.*;

public class Clob_Insert_Ex {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		FileReader fr=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","Mysql134");
			pst = con.prepareStatement("insert into apps values(?,?)");
			pst.setString(1, "app1");
			File f = new File("E:/trash_docs/applicationContext.xml");
			fr = new FileReader(f);
			pst.setCharacterStream(2, fr, f.length());
			int rowCount = pst.executeUpdate();
			if(rowCount==1) {
				System.out.println("Document Inserted Successfully");
			}else {
				System.out.println("Document Inserted Failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				pst.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}

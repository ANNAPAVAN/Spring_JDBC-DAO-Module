package com.pavan.jdbc;
import java.sql.*;
import java.io.*;
public class Blob_Insert_Ex {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement pst = null;
		FileInputStream fis = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","Mysql134");
			pst = con.prepareStatement("insert into emp10 values(?,?)");
			pst.setInt(1,111);
			
			File file = new File("C:\\Users\\pavan\\OneDrive\\Desktop\\cat.jpeg");
			fis = new FileInputStream(file);
			pst.setBinaryStream(2, fis , (int)file.length());   //long also accepted
			int rowCount = pst.executeUpdate();
			if(rowCount==1) {
				System.out.println("Employee 111 inserted Successsfully");
				
			}else {
				System.out.println("Employee 111 insertion Failure");
			}
			
		}catch(Exception e){
			System.out.println("Employee insertion Failure");
			e.printStackTrace();
		}finally {
			try {
				fis.close();
				pst.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}

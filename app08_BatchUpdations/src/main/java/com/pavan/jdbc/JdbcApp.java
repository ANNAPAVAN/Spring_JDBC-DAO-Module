package com.pavan.jdbc;
import java.sql.*;
public class JdbcApp {

	public static void main(String[] args)throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","Mysql134");
		
		//---CreateStatement---
/*		Statement st = con.createStatement();
		st.addBatch("insert into Employee values(444,'DDD',1000,'Hyderabad')");
		st.addBatch("update Employee set esal = esal+500 where esal<10000");
		st.addBatch("delete from Employee where eno=111");
		int[] rowCounts = st.executeBatch();
		for(int i=0;i<rowCounts.length;i++)
		{
			System.out.println("Row Count : "+rowCounts[i]);
		}
		st.close();
		con.close();
*/
		
		//---PreparedStatement---
		
		PreparedStatement pst = con.prepareStatement("insert into Employee values(?,?,?,?)");
		pst.setInt(1,111);
		pst.setString(2, "AAA");
		pst.setFloat(3, 5000);
		pst.setString(4,"Chennai");
		pst.addBatch();
		
		pst.setInt(1,222);
		pst.setString(2, "BBB");
		pst.setFloat(3, 6000);
		pst.setString(4,"Hyd");
		pst.addBatch();
		
		pst.setInt(1,333);
		pst.setString(2, "CCC");
		pst.setFloat(3, 7000);
		pst.setString(4,"Vizag");
		pst.addBatch();
		
		pst.setInt(1,444);
		pst.setString(2, "DDD");
		pst.setFloat(3, 8000);
		pst.setString(4,"Hyd");
		pst.addBatch();
		
		int[] rowCounts = pst.executeBatch();
		
		for(int rowCount:rowCounts)
		{
			System.out.println("Row Count : "+rowCount);
		}
		pst.close();
		con.close();

	}

}

/*
	DELIMITER //
	
	CREATE FUNCTION getAVG(no1 INT, no2 INT)
	RETURNS FLOAT
	READS SQL DATA
	BEGIN
	    DECLARE sal1 FLOAT;
	    DECLARE sal2 FLOAT;
	
	    SELECT esal INTO sal1 FROM Employee WHERE eno = no1;
	    SELECT esal INTO sal2 FROM Employee WHERE eno = no2;
	
	    RETURN (sal1 + sal2) / 2;
	END //
	
	DELIMITER ;


*/

package com.pavan.jdbc;

import java.sql.*;


public class FunctionEx {

	public static void main(String[] args)throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","Mysql134");
		CallableStatement cst = con.prepareCall("{? = call getAVG(?,?)}");
		cst.setInt(2, 222);  // for IN parameter
		cst.setInt(3, 333);   // for IN parameter
		cst.registerOutParameter(1, Types.FLOAT);  // for OUT parameter
		cst.execute();
		System.out.println("Average Salary of id:222 & id:333 from Employee : "+cst.getFloat(1));
		
		cst.close();
		con.close();

	}

}

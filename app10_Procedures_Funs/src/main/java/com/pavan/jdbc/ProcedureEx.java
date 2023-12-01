/*
	DELIMITER //
	
	CREATE PROCEDURE getSal(IN eno_param INT, OUT sal_param FLOAT)
	BEGIN
	    SELECT esal INTO sal_param FROM employee WHERE eno = eno_param;
	END //
	
	DELIMITER ;
 */


package com.pavan.jdbc;

import java.sql.*;


public class ProcedureEx {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","Mysql134");
		CallableStatement cst = con.prepareCall("{call getSal(?,?)}");
		cst.setInt(1, 222);
		cst.registerOutParameter(2, Types.FLOAT);
		cst.execute();  //procedure will be executed and value come to out type parameter
		System.out.println("Salary  : "+cst.getFloat(2));
		cst.close();
		con.close();

	}

}

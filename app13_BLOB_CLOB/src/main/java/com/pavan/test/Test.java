/*  create table emp20(
    -> eno Int primary key,
    -> ename varchar(100),
    -> emp_image blob,
    -> emp_resume longtext);
 */

package com.pavan.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.pavan.dao.*;
import com.pavan.dto.*;
import java.io.*;
public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/pavan/resources/applicationContext.xml");
		EmployeeDao employeeDao = (EmployeeDao)context.getBean("employeeDao");
		
		//INSERT operation
/*		
		Employee emp =  new Employee();
		emp.setEno(111);
		emp.setEname("AAA");
		emp.setEmp_image(new File("E:/trash_docs/cat.jpeg"));
		emp.setEmp_resume(new File("E:/trash_docs/Akhil.Gadhasu_Resume.docx"));
		
		employeeDao.insertEmployee(emp);
		System.out.println("Employee inserted successfully");
*/
		
		//READ operation
		
		Employee emp = employeeDao.readEmployee(111);
		System.out.println("Employee Details");
		System.out.println("---------------------");
		System.out.println("Employee No    : "+emp.getEno());
		System.out.println("Employee Name  : "+emp.getEname());
		System.out.println("Employee Image : "+emp.getEmp_image().getAbsolutePath());
		System.out.println("Employee Resume: "+emp.getEmp_resume().getAbsolutePath());
		
	}

}

package com.pavan.test;
import java.util.*;
import com.pavan.dao.*;
import com.pavan.dto.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/pavan/resources/applicationContext.xml");
		EmployeeDao empDao = (EmployeeDao)context.getBean("employeeDao");
		
		//ADD operation
		Employee emp = new Employee();
		emp.setEno(114);
		emp.setEname("AAA");
		emp.setEsal(1200);
		emp.setEaddr("Us");
		String status = empDao.add(emp);
		System.out.println(status);

		
/*		//SEARCH operation
		Employee emp = empDao.search(222);
		if(emp==null)
		{
			System.out.println("Employee Not Existed");
		}else {
			System.out.println("Employee Details");
			System.out.println("--------------");
			System.out.println("Employee no     : "+emp.getEno());
			System.out.println("Employee Name   : "+emp.getEname());
			System.out.println("Employee Salary : "+emp.getEsal());
			System.out.println("Employee Address: "+emp.getEaddr());
		}
*/
		
/*		//GET ALL EMPLOYEES
		List<Employee> empsList = empDao.getAllEmployees();
		System.out.println("ENO\tENAME\tESA\tEADDR");
		System.out.println("----------------------------------");
		for(Employee emp:empsList)
		{
			System.out.print(emp.getEno()+"\t");
			System.out.print(emp.getEname()+"\t");
			System.out.print(emp.getEsal()+"\t");
			System.out.print(emp.getEaddr()+"\t");
			System.out.println();
		}
*/
		
/*		//UPDATE
		Employee emp = new Employee();
		emp.setEno(111);
		emp.setEname("XXX");
		emp.setEsal(90000);
		emp.setEaddr("Hyd");
		String status = empDao.update(emp);
		System.out.println(status);
*/
		
/*		//DELETE
		String status = empDao.delete(121);
		System.out.println(status);
*/
		
		


	}

}

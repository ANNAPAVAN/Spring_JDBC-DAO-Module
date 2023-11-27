package com.pavan.test;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pavan.dao.EmployeeDao;
import com.pavan.dto.Employee;

public class Test {

	public static void main(String[] args)throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/pavan/resources/applicationContext.xml");
		EmployeeDao employeeDao = (EmployeeDao)context.getBean("employeeDao"); 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.println();
			System.out.println("1. Add Employee");
			System.out.println("2. Search Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit");
			
			System.out.print("Your Option[1,2,3,4,5] : ");
			int option = Integer.parseInt(br.readLine());
			System.out.println();
			Employee emp = null;
			String status="";
			int eno=0;
			String ename="";
			float esal=0;
			String eaddr="";
			switch(option)
			{
				case 1:
					System.out.println("==== ADD Module ====");
					System.out.print("Employee Number : ");
					eno = Integer.parseInt(br.readLine());
					System.out.print("Employee Name : ");
					ename = br.readLine();
					System.out.print("Employee Salary : ");
					esal = Float.parseFloat(br.readLine());
					System.out.print("Employee Address : ");
					eaddr = br.readLine();
					
					emp = new Employee();
					emp.setEno(eno);
					emp.setEname(ename);
					emp.setEsal(esal);
					emp.setEaddr(eaddr);
					status = employeeDao.add(emp);
					System.out.println(status);
					break;
				case 2:
					System.out.println("==== SEARCH Module ====");
					System.out.print("Employee Number : ");
					eno = Integer.parseInt(br.readLine());
					emp = employeeDao.search(eno);
					if(emp==null)
					{
						System.out.println("Employee Not Existed");
					}
					else
					{
						System.out.println("Employee Details ");
						System.out.println("-----------------");
						System.out.println("Employee Number  : "+emp.getEno());
						System.out.println("Employee Name    : "+emp.getEname());
						System.out.println("Employee Salary  : "+emp.getEsal());
						System.out.println("Employee Address : "+emp.getEaddr());
					}
					
					break;
				case 3:
					System.out.println("==== UPDATE Module ====");
					System.out.print("Employee Number : ");
					eno = Integer.parseInt(br.readLine());
					emp = employeeDao.search(eno);
					if(emp==null)
					{
						System.out.println("Employee Not Existed");
					}
					else {
						System.out.println("Employee Number   : "+emp.getEno());
						System.out.print("Employee Name [Old value : "+emp.getEname()+" ] : ");
						String ename_New = br.readLine();
						System.out.print("Employee Salary [Old value : "+emp.getEsal()+" ] : ");
						float esal_New = Float.parseFloat(br.readLine());
						System.out.print("Employee Address [Old value : "+emp.getEaddr()+" ] :");
						String eaddr_New = br.readLine();
						
						Employee emp_New = new Employee();
						emp_New.setEno(eno);
						emp_New.setEname(ename_New);
						emp_New.setEsal(esal_New);
						emp_New.setEaddr(eaddr_New);
						
						status = employeeDao.update(emp_New);
						System.out.println(status);
					}
					break;
				case 4:
					System.out.println("==== DELETE Module ====");
					System.out.print("Employee Number : ");
					eno = Integer.parseInt(br.readLine()); 
					status = employeeDao.delete(eno);
					System.out.println(status);
					break;
				case 5:
					System.out.println("***** Thank You... Visit Again..!! *****");
					System.exit(0);
					break;
				default:
					System.out.println("Enter valid option");
					break;
					
				
			}
			
		}
		
		
		
		
		
		
		
		
		
/*		//ADD operation 
		Employee emp = new Employee();
		emp.setEno(222);
		emp.setEname("BBB");
		emp.setEsal(56000);
		emp.setEaddr("Hyd");
		String status = employeeDao.add(emp);
		System.out.println(status);
*/	
/*		//SEARCH operation
		
		Employee emp = employeeDao.search(111);
		if(emp==null)
		{
			System.out.println("Employee Not Existed");
		}
		else
		{
			System.out.println("Employee Details");
			System.out.println("----------------");
			System.out.println("Employee Number : "+emp.getEno());
			System.out.println("Employee Name   : "+emp.getEname());
			System.out.println("Employee Salary : "+emp.getEsal());
			System.out.println("Employee Address: "+emp.getEaddr());
		}
*/
/*		//UPDATE operation
		Employee emp = new Employee();
		emp.setEno(111);
		emp.setEname("XXX");
		emp.setEsal(9000);
		emp.setEaddr("Chennai");
		String status = employeeDao.update(emp);
		System.out.println(status);
*/
/*		//DELETE operation
		String status = employeeDao.delete(111);
		System.out.println(status);
*/
		
		
	}

}





















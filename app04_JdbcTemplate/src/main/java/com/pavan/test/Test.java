package com.pavan.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.pavan.dao.*;
import com.pavan.dto.Student;
import java.io.*;

public class Test {

	public static void main(String[] args)throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/pavan/resources/applicationContext.xml");
		StudentDao studentDao =(StudentDao)context.getBean("studentDao");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.println();
			System.out.println("1. Add Student");
			System.out.println("2. Search Student");
			System.out.println("3. Update Student");
			System.out.println("4. Delete Student");
			System.out.println("5. Exit");
			System.out.print("Enter Your OPtion[1,2,3,4,5] :");
			int option = Integer.parseInt(br.readLine());
			String sid="";
			String sname="";
			String saddr="";
			String status="";
			Student std=null;
			switch(option)
			{
			case 1:
				System.out.println("---ADD MODULE---");
				System.out.print("Student Id      : ");
				sid = br.readLine();
				System.out.print("Student Name    : ");
				sname = br.readLine();
				System.out.print("Student Address : ");
				saddr = br.readLine();
				
				std = new Student();	
				std.setSid(sid);
				std.setSname(sname);
				std.setSaddr(saddr);
				
				status = studentDao.add(std);
				System.out.println(status);
				break;
			case 2:
				System.out.println("---SEARCH MODULE---");
				System.out.print("Student Id :");
				sid = br.readLine();
				
				std = studentDao.search(sid);
				if(std==null)
				{
					System.out.println("Student Not Existed");
				}else {
					System.out.println("Student Details");
					System.out.println("---------------");
					System.out.println("Student Id     : "+std.getSid());
					System.out.println("Student Name   : "+std.getSname());
					System.out.println("Student Address: "+std.getSaddr());
				}
				break;
			case 3:
				System.out.println("---UPDATE MODULE---");
				System.out.print("Student Id :");
				sid = br.readLine();
				std = studentDao.search(sid);
				if(std==null)
				{
					System.out.println("Student Not Existed");
				}else {
					System.out.print("Student Name [old : "+std.getSname()+" ] : ");
					sname = br.readLine();
					System.out.print("Student Address [old : "+std.getSaddr()+" ] : ");
					saddr = br.readLine();
					std = new Student();
					std.setSid(sid);
					std.setSname(sname);
					std.setSaddr(saddr);
					
					status = studentDao.update(std);
					System.out.println(status);
				}
				break;
			case 4:
				System.out.println("---DELETE MODULE---");
				System.out.print("Student Id :");
				sid = br.readLine();
				status = studentDao.delete(sid);
				System.out.println(status);
				break;
			case 5:
				System.out.println("************** Thank You Visit Again  ************");
				System.exit(0);
			default:
				System.out.println("Enter valid option");
				break;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
/*		//ADD operation
		Student std = new Student();
		std.setSid("s-222");
		std.setSname("BBBBB");
		std.setSaddr("HYDERABAD");
		
		String status = studentDao.add(std);
		System.out.println(status);
*/
		//SEARCH operation
/*		Student std = studentDao.search("s-111");
		if(std==null)
		{
			System.out.println("Student NOt Existed");
		}else {
			System.out.println("Student Details");
			System.out.println("---------------");
			System.out.println("Student Id     : "+std.getSid());
			System.out.println("Student Name   : "+std.getSname());
			System.out.println("Student Address: "+std.getSaddr());
		}
*/
/*		//UPDATE operation
		Student std = new Student();
		std.setSid("s-111");
		std.setSname("XXXXX");
		std.setSaddr("Chennai");
		String status = studentDao.update(std);
		System.out.println(status);
*/
/*		//DELETE opertaion
		String status = studentDao.delete("s-222");
		System.out.println(status);
*/
	}

}

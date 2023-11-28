package com.pavan.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.pavan.dao.*;
import com.pavan.dto.Employee;
import java.util.*;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/pavan/resources/applicationContext.xml");
		EmployeeDao empDao = (EmployeeDao)context.getBean("empDao");
		
		//Add		
 
		Employee e1 = new Employee();
		e1.setEno(111);
		e1.setEname("AAA");
		e1.setEsal(10000);
		e1.setEaddr("Hyd");
		
		Employee e2 = new Employee();
		e2.setEno(222);
		e2.setEname("BBB");
		e2.setEsal(20000);
		e2.setEaddr("Hyd");
		
		Employee e3 = new Employee();
		e3.setEno(333);
		e3.setEname("CCC");
		e3.setEsal(30000);
		e3.setEaddr("Hyd");
		
		Employee e4 = new Employee();
		e4.setEno(444);
		e4.setEname("DDD");
		e4.setEsal(40000);
		e4.setEaddr("Hyd");
		
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(e1);
		empList.add(e2);
		empList.add(e3);
		empList.add(e4);
		
		int rowCounts[] = empDao.insert(empList);
		for(int rowCount:rowCounts)
		{
			System.out.println("Row Count : "+rowCount);
		}

		
/*		//Update
		int rowCounts[] = empDao.update();
		for(int rowCount:rowCounts)
		{
			System.out.println("Row Count : "+rowCount);
		}
*/
	}

}

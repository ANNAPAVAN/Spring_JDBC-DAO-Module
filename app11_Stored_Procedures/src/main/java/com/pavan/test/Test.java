package com.pavan.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.pavan.dao.*;
import com.pavan.dto.Employee;
public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/pavan/resources/applicationContext.xml");
		EmployeeDao employeeDao = (EmployeeDao)context.getBean("employeeDao");
	
		Employee emp = new Employee();
		emp.setEno(666);
		emp.setEname("KKK");
		emp.setEsal(20000);
		emp.setEaddr("HYD");
		employeeDao.create(emp);
		System.out.println("Employee "+emp.getEno()+" Inserted Successfully");
	
/*		
		System.out.println("222 salary  : "+employeeDao.getEmpSal(333));
*/
/*		Employee emp = new Employee();
		emp.setEno(222);
		emp.setEname("XXX");
		emp.setEsal(19000);
		emp.setEaddr("Delhi");
		employeeDao.updateEmployee(emp);
		System.out.println("Employee 222 updated Successfully");
*/
/*		employeeDao.deleteEmployee(666);
		System.out.println("Employee 222 deleted Successfully");
*/
	}

}

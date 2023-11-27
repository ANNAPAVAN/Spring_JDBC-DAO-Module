package com.pavan.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.pavan.dao.*;
import com.pavan.dto.*;
public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/pavan/resources/applicationContext.xml");
		CustomerDao customerDao = (CustomerDao)context.getBean("customerDao");
		
		//ADD operation
		Customer customer = new Customer();
		customer.setCid("c-111");
		customer.setCname("pavan");
		customer.setCaddr("Hyd");
		String status = customerDao.add(customer);
		System.out.println(status);

		
		
/*		//SEARCH operation
		Customer cust = customerDao.search("c-111");
		if(cust==null)
		{
			System.out.println("Customer Not Existed");
		}else {
			System.out.println("Customer Details");
			System.out.println("---------------");
			System.out.println("Customer ID  : "+cust.getCid());
			System.out.println("Customer Name: "+cust.getCname());
			System.out.println("Customer Addr: "+cust.getCaddr());
		}
*/
		
		
/*		//UPDATE operation
		Customer customer = new Customer();
		customer.setCid("c-111");
		customer.setCname("ANNA");
		customer.setCaddr("ChennaiIPL");
		String status = customerDao.update(customer);
		System.out.println(status);
*/
		
		
/*		//DELETE operation
		String status = customerDao.delete("c-111");
		System.out.println(status);
*/
	}

}

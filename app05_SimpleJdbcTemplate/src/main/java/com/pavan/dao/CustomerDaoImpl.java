package com.pavan.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import java.util.*;
import com.pavan.dto.Customer;
import com.pavan.mapper.*;

public class CustomerDaoImpl implements CustomerDao {

	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}

	public String add(Customer customer) {
		String status="";
		try {
			Customer cust = search(customer.getCid());
			if(cust==null)
			{
				String query = "insert into customer values(?,?,?)";
				int rowCount = simpleJdbcTemplate.getJdbcOperations().update(query,new Object[] {customer.getCid(),customer.getCname(),customer.getCaddr()});
				if(rowCount==1) {
					status="Customer inserted Successfully";
				}else {
					status = "Customer Insertion Failure";
				}
			}else {
				status = "Customer Existed Already";
			}
		}catch(Exception e)
		{
			status = "Customer Insertion Failure";
			e.printStackTrace();
		}
		return status;
	}

	public Customer search(String cid) {
		Customer customer=null;
		try {
			String query = "Select * from customer where cid='"+cid+"'";
			List<Customer> customersList = simpleJdbcTemplate.getJdbcOperations().query(query,new CustomerMapper());
			if(customersList.isEmpty())
			{
				customer=null;
			}else {
				customer = customersList.get(0);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public String update(Customer customer) {
		String status="";
		try {
			Customer cust = search(customer.getCid());
			if(cust==null)
			{
				status = "Customer Not Existed";
			}else {
				String query="update customer set cname=? ,caddr=? where cid=?";
				int rowCount = simpleJdbcTemplate.getJdbcOperations().update(query,new Object[] {customer.getCname(),customer.getCaddr(),customer.getCid()});
				if(rowCount==1)
				{
					status="Customer Updation Success";
				}else {
					status = "Customer Updation Failure";
				}
			}
		}catch(Exception e) {
			status = "Customer Updation Failure";
			e.printStackTrace();
		}
		return status;
	}

	public String delete(String cid) {
		String status="";
		try {
			Customer cust=search(cid);
			if(cust==null) {
				status ="Customer Not Existed";
			}else {
				String query="delete from customer where cid=?";
				int rowCount = simpleJdbcTemplate.getJdbcOperations().update(query,new Object[] {cid});
				if(rowCount==1)
				{
					status="Customer Deletion Success";
				}else {
					status = "Customer Deletion Failure";
				}
			}
		}catch(Exception e) {
			
		}
		return status;
	}

}

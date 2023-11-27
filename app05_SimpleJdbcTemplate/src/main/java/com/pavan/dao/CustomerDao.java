package com.pavan.dao;
import com.pavan.dto.*;
public interface CustomerDao {
	public String add(Customer customer);
	public Customer search(String cid);
	public String update(Customer customer);
	public String delete(String cid);
	
}

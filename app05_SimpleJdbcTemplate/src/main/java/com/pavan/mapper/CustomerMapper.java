package com.pavan.mapper;
import com.pavan.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class CustomerMapper implements ParameterizedRowMapper<Customer> {
	public Customer mapRow(ResultSet rs,int index) throws SQLException{
		Customer customer = new Customer();
		customer.setCid(rs.getString("cid"));
		customer.setCname(rs.getString("cname"));
		customer.setCaddr(rs.getString("caddr"));
		return customer;
	}
}

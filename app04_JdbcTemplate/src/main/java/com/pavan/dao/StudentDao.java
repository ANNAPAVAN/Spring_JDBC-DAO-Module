package com.pavan.dao;
import com.pavan.dto.*;
public interface StudentDao {
	public String add(Student std);
	public Student search(String sid);
	public String update(Student std);
	public String delete(String sid);
	
}

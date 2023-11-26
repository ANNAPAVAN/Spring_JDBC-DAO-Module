package com.pavan.dao;

import com.pavan.dto.Student;

public interface StudentDao {
	public String add(Student std);
	public Student search(String sid);
	public String delete(String sid);
}

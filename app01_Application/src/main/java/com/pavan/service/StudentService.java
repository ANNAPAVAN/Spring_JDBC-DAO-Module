package com.pavan.service;
import com.pavan.dto.*;

public interface StudentService {
	public String addStudent(Student std);
	public Student searchStudent(String sid);
	public String deleteStudent(String sid);
}

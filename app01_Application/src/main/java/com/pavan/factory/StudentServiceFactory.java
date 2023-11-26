package com.pavan.factory;

import com.pavan.service.StudentService;
import com.pavan.service.StudentServiceImpl;

public class StudentServiceFactory {
	private static StudentService studentService;
	static {
		studentService = new StudentServiceImpl();
	}
	public static StudentService getStudentService()
	{
		return studentService;
	}
}

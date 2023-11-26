package com.pavan.factory;

import com.pavan.dao.StudentDao;
import com.pavan.dao.StudentDaoImpl;

public class StudentDaoFactory {
	private static StudentDao studentDao;
	static {
		studentDao = new StudentDaoImpl();
	}
	public static StudentDao getStudentDao()
	{
		return studentDao;
	}
}

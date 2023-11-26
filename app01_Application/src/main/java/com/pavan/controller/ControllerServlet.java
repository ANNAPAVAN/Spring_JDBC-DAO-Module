package com.pavan.controller;
import com.pavan.factory.*;
import com.pavan.dto.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.pavan.service.StudentService;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String req_Path = request.getRequestURI();
//		System.out.println(req_Path);
/*   OUTPUT in console when we click respective buttons
   /app01_Application/add.do
   /app01_Application/search.do
   /app01_Application/delete.do
*/
		StudentService studentService = StudentServiceFactory.getStudentService();
		if(req_Path.endsWith("add.do"))
		{
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String saddr = request.getParameter("saddr");
			
			Student std = new Student();
			std.setSid(sid);
			std.setSname(sname);
			std.setSaddr(saddr);
			String status = studentService.addStudent(std);
			
			RequestDispatcher requestDispatcher=null;
			if(status.equals("success"))
			{
				requestDispatcher = request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("failure"))
			{
				requestDispatcher = request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("existed"))
			{
				requestDispatcher = request.getRequestDispatcher("existed.html");
				requestDispatcher.forward(request, response);
			}
		}
		if(req_Path.endsWith("search.do"))
		{
			String sid=request.getParameter("sid");
			Student std = studentService.searchStudent(sid);
			RequestDispatcher requestDispatcher = null;
			if(std==null)
			{
				requestDispatcher = request.getRequestDispatcher("notexisted.html");
				requestDispatcher.forward(request, response);
			}
			else
			{
				request.setAttribute("std", std);
				requestDispatcher = request.getRequestDispatcher("display.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		if(req_Path.endsWith("delete.do"))
		{
			String sid = request.getParameter("sid");
			String status = studentService.deleteStudent(sid);
			RequestDispatcher requestDispatcher = null;
			if(status.equals("success"))
			{
				requestDispatcher = request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("failure"))
			{
				requestDispatcher = request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("notexisted"))
			{
				requestDispatcher = request.getRequestDispatcher("notexisted.html");
				requestDispatcher.forward(request, response);
			}
		}
	}

}

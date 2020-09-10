package com.loginsystem.api;

import com.loginsystem.db.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/api/v1/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO insert the posted data into table
		response.setContentType("text/html;charset=UTF-8");


		UserInf currentUser = new UserInf();

		currentUser.setId(Integer.parseInt(request.getParameter("userId")));
		currentUser.setPw(new String(request.getParameter("userPw").getBytes("ISO8859-1"),"UTF-8"));
		currentUser.setName(new String(request.getParameter("userName").getBytes("ISO8859-1"),"UTF-8"));
		currentUser.setDeptNo(Integer.parseInt(request.getParameter("userDeptNo")));
		currentUser.setRgstDate("2020-09-07");

		currentUser.insertIntoDb();

		response.getWriter().append("user id is : ").append(request.getParameter("userId"));

	}

}

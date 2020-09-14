package com.loginsystem.REST.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loginsystem.REST.db.UserInf;

/**
 * Servlet implementation class Login
 */
@WebServlet("/api/v1.0/Password")
public class Password extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Password() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* TODO
		 * check the password correct or not
		 * content-type:application/x-www-form-urlencoded
		 * userId=(int)&userPw=(String)
		 */

		response.setContentType("text/html;charset=UTF-8");

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss.SSS");

		try {
			UserInf checkUser = new UserInf();
			checkUser.setId(Integer.parseInt(request.getParameter("userId")));
			checkUser.setPw(new String(request.getParameter("userPw").getBytes("ISO8859-1"),"UTF-8"));
			String checkResult = checkUser.checkIdPw();
			response.getWriter().append(checkResult+"\n");
			response.getWriter().append(ft.format(dNow));
		} catch (SQLException ex) {
			String sqlEx = "SQLException\n";
			response.getWriter().append(sqlEx);
		}

	}

}

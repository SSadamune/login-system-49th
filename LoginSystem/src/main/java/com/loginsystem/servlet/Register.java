package com.loginsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loginsystem.db.UserInf;

/**
 * Servlet implementation class Register
 */
@WebServlet("/api/v1.0/Register")
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
		/* TODO
		 * insert the posted data into table
		 * content-type:application/x-www-form-urlencoded
		 * userId=(int)&userName=(String)&userDeptNo=(int)&userPw=(String)
		 */
		response.setContentType("text/html;charset=UTF-8");

		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

		//Validation未実装
		try {
			UserInf currentUser = new UserInf();

			currentUser.setId(Integer.parseInt(request.getParameter("userId")));
			currentUser.setPw(new String(request.getParameter("userPw").getBytes("ISO8859-1"),"UTF-8"));
			currentUser.setName(new String(request.getParameter("userName").getBytes("ISO8859-1"),"UTF-8"));
			currentUser.setDeptNo(Integer.parseInt(request.getParameter("userDeptNo")));
			currentUser.setRgstDate(ft.format(dNow));

			currentUser.insertIntoDb();
			response.getWriter().append("inserted correctly");
		} catch (SQLException ex) {
			String sqlEx = "SQLException: \n";
			for(Throwable e : ex ) {
				sqlEx += e;
			}
			response.getWriter().append(sqlEx);
		}

	}

}

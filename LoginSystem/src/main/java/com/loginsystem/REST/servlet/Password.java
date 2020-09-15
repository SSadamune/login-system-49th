package com.loginsystem.REST.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loginsystem.REST.db.UserInf;

/**
 * Servlet implementation class Login
 */
@WebServlet("/api/v1.0/password")
public class Password extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Password() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* TODO
		 * check the password is correct or not
		 * content-type:application/x-www-form-urlencoded
		 * userId=(int)&userPw=(String)
		 */

		response.setContentType("text/html;charset=UTF-8");

		try {
			UserInf checkUser = new UserInf();
			checkUser.setId(Integer.parseInt(request.getParameter("userId")));
			checkUser.setPw(new String(request.getParameter("userPw").getBytes("ISO8859-1"),"UTF-8"));
			int checkResult = checkUser.checkIdPw();

			switch (checkResult) {
			case 200:
				response.setStatus(200);
				//Date dNow = new Date();
				//SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss.SSS");
				//response.getWriter().append(ft.format(dNow));
				break;
			case 401:
				response.sendError(401, "password incorrect" );
				break;
			case 404:
				response.sendError(404, "userId: " + checkUser.getId() + " not found" );
				break;
			}

		} catch (SQLException ex) {
			response.sendError(500, "unexpected SQL exception\n"
					+ "sql state = " + ex.getSQLState() +"\n"
					+ "error message: " + ex.getLocalizedMessage());
		}
	}
}

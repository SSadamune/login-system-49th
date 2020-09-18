package com.loginsystem.REST.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.loginsystem.REST.db.UserInfo;
import com.loginsystem.REST.util.JsonResponse;
import com.loginsystem.REST.util.PostReader;

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
		response.setContentType("application/json;charset=UTF-8");

		// request to json string
		String jsonStrPost = PostReader.toJsonStr(request);

		/* validation wait to do */

		// json string to object
		UserInfo checkUser = new Gson().fromJson(jsonStrPost, UserInfo.class);

		try {
			int checkResult = checkUser.checkIdPw();

			switch (checkResult) {
			case 200:
				response.setStatus(200);
				response.getWriter().write(JsonResponse.message("OK", "password correct"));
				break;
			case 401:
				response.setStatus(401);
				response.getWriter().write(JsonResponse.message("Auth_failured", "password incorrect"));
				break;
			case 404:
				response.setStatus(404);
				response.getWriter().write(JsonResponse.message("not_found", "[id] not found"));
				break;
			}

		} catch (SQLException ex) {
			response.getWriter().write(JsonResponse.message("SQL_exception"
					, "unexpected SQL Exception: " + ex.getSQLState()));
			System.out.println(ex.getLocalizedMessage());

		}

	}

}

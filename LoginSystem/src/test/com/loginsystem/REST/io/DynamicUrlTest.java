package com.loginsystem.REST.io;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/v1.0/Test/*")
public class DynamicUrlTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DynamicUrlTest() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getPathInfo().substring(1);
		System.out.println(userId);
		response.getWriter().append("user : " + userId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

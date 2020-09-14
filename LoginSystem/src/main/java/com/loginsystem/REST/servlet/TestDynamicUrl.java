package com.loginsystem.REST.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/v1.0/Test/*")
public class TestDynamicUrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TestDynamicUrl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String getPath = request.getPathInfo();
		String userId = getPath == null ? request.getParameter("userId") : request.getPathInfo().substring(1);
		System.out.println(getPath);
		response.getWriter().append("user : " + userId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

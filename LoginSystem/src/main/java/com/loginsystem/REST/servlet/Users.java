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

@WebServlet(value = {"/api/v1.0/Users","/api/v1.0/Users/*"})
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Users() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* TODO
		 * response the data of user
		 * accept URL like "/Users?userId=1004" or "/Users/1004"
		 */

		String urlPath = request.getPathInfo();
		response.setContentType("text/html;charset=UTF-8");

		try {
			String strUserId = urlPath == null ? request.getParameter("userId") : request.getPathInfo().substring(1);
			int userId = Integer.parseInt(strUserId);

			UserInf getUser = new UserInf();
			getUser.selectFromDb(userId);
			response.setStatus(200);
			response.getWriter().append("user : " + getUser.getId() +"\n");
			response.getWriter().append("name : " + getUser.getName() +"\n");

		} catch (SQLException ex) {
			response.sendError(500, "unexpected SQL exception" );
		}

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
			UserInf postUser = new UserInf();

			postUser.setId(Integer.parseInt(request.getParameter("userId")));
			postUser.setPw(new String(request.getParameter("userPw").getBytes("ISO8859-1"),"UTF-8"));
			postUser.setName(new String(request.getParameter("userName").getBytes("ISO8859-1"),"UTF-8"));
			postUser.setDeptNo(Integer.parseInt(request.getParameter("userDeptNo")));
			postUser.setRgstDate(ft.format(dNow));

			postUser.insertIntoDb();
			response.setStatus(201);//Created

		} catch (SQLException ex) {
			response.sendError(500, "unexpected SQL exception" );
		}

	}

}

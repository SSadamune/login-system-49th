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

import com.google.gson.Gson;
import com.loginsystem.REST.db.UserInfo;
import com.loginsystem.REST.util.PostReader;

@WebServlet(value = {"/api/v1.0/users","/api/v1.0/users/*"})
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Users() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* TODO
		 * response information of user
		 * accept URL like "/users?id=1004" or "/users/1004"
		 */

		response.setContentType("text/html;charset=UTF-8");

		// urlPath = null or = "/1004", depend on the URL format
		String urlPath = request.getPathInfo();
		String strUserId = urlPath == null ? request.getParameter("id") : urlPath.substring(1);

		int userId = Integer.parseInt(strUserId);

		try {
			UserInfo getUser = new UserInfo();
			getUser.selectFromDb(userId);
			response.setStatus(200);
			response.getWriter().append("{\"status\": \"OK\", \"data\": ");
			response.getWriter().append(getUser.toJson());
			response.getWriter().append("}");

		} catch (SQLException ex) {
			// sql state: www.postgresql.org/docs/8.4/errcodes-appendix.html
			if (ex.getSQLState().equals("24000")) {
				// INVALID CURSOR STATE
				response.sendError(404, "userId: " + userId + " not found");

			} else {
				response.sendError(500, "unexpected SQL exception\n"
						+ "sql state = " + ex.getSQLState() +"\n"
						+ "error message: " + ex.getLocalizedMessage());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* TODO
		 * insert data into table
		 * content-type:application/x-www-form-urlencoded
		 * userId=(int)&userName=(String)&userDeptNo=(int)&userPw=(String)
		 */
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();

		// request to json string
		String jsonStrPost = PostReader.toJsonStr(request);

		// Validation未実装
		// json string to object
		UserInfo postUser = gson.fromJson(jsonStrPost, UserInfo.class);

		// add register date
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		postUser.setRgstDate(ft.format(dNow));

		System.out.println(gson.toJson(postUser));

		// insert data to DB
		try {
			postUser.insertIntoDb();
			response.setStatus(201);
			response.getWriter().write("{\"status\": \"OK\"}");

		} catch (SQLException ex) {
			// sql state: www.postgresql.org/docs/8.4/errcodes-appendix.html
			if (ex.getSQLState().equals("23503")) {
				// FOREIGN KEY VIOLATION
				response.setStatus(400);
				response.getWriter().write("{\"status\": \"dept_no unknown\"}");
				//response.sendError(400, "deptNo: " + postUser.getDeptNo() + " unknown");

			} else if (ex.getSQLState().equals("23505")) {
				// UNIQUE VIOLATION
				response.setStatus(400);
				response.getWriter().write("{\"status\": \"id exists\"}");
				//response.sendError(400, "userId: " + postUser.getId() + " exists");

			} else {
				response.setStatus(500);
				response.getWriter().write("{\"status\": \"unexpected exception\"}");
//				response.sendError(500, "unexpected SQL exception\n"
//						+ "sql state = " + ex.getSQLState() +"\n"
//						+ "error message: " + ex.getLocalizedMessage());
			}
		}

	}

}

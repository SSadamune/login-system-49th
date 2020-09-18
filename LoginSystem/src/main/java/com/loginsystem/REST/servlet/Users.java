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
import com.loginsystem.REST.util.JsonResponse;
import com.loginsystem.REST.util.PostReader;
import com.loginsystem.REST.util.ValidChecker;
import com.loginsystem.REST.util.insertExceptionResponse;

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
		response.setContentType("application/json;charset=UTF-8");

		// judge the URL pattern like "/users/1004" or "/users?id=1004"
		String urlPath = request.getPathInfo();
		String strUserId = urlPath == null ? request.getParameter("id") : urlPath.substring(1);;

		// check validation of id
		if (!(ValidChecker.idValid(strUserId))) {
			response.setStatus(400);
			response.getWriter().write(JsonResponse.message("parameter_invalid",
					"[id] invalid. Must be a number less than 9 digits."));
			return ;
		}

		int userId = Integer.parseInt(strUserId);

		// select from DB
		try {
			UserInfo getUser = new UserInfo();
			String jsonUser = getUser.selectFromDb(userId);
			response.setStatus(200);
			response.getWriter().write(JsonResponse.getUser("got_it!", jsonUser));

		} catch (SQLException ex) {
			// sql state: www.postgresql.org/docs/8.4/errcodes-appendix.html
			if (ex.getSQLState().equals("24000")) {
				// INVALID CURSOR STATE
				response.setStatus(404);
				response.getWriter().write(JsonResponse.message("not_found", "[id] = " + userId + " not found"));

			} else {
				response.setStatus(500);
				response.getWriter().write(JsonResponse.message("SQL_exception"
						, "unexpected SQL Exception: " + ex.getSQLState()));
				System.out.println(ex.getLocalizedMessage());
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
		response.setContentType("application/json;charset=UTF-8");

		// from request to Userinfo Object
		// POST body example: {"id":1009,"pw":"testpassword","name":"名前1009","deptNo":1002}
		UserInfo postUser = new UserInfo();
		try {
			postUser = new Gson().fromJson(PostReader.toJsonStr(request), UserInfo.class);

		} catch (Exception e) {
			// data-type incorrect, such as id = "apple"
			response.setStatus(400);
			response.getWriter().write(JsonResponse.message("parameter_invalid",
					"[(int)id], [(str)pw], [(str)name], [(int)dept_no] required"));
			return ;
		}

		ValidChecker vc = new ValidChecker();
		// check validation of id, pw, name, deptNo
		if (!(vc.objRegisterValid(postUser))) {
			response.setStatus(400);
			response.getWriter().write(JsonResponse.message("parameter_invalid", vc.getMessage()));
			return ;
		}

		// set register date
		postUser.setRgstDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		// insert data to DB
		try {
			postUser.insertIntoDb();
			response.setStatus(201);
			response.getWriter().write(JsonResponse.message("OK", "register successfully"));

		} catch (SQLException e) {
			// sql state: www.postgresql.org/docs/8.4/errcodes-appendix.html
			insertExceptionResponse er = new insertExceptionResponse(e);
			response.setStatus(er.getStatusCode());
			response.getWriter().write(er.getErrorMessage());
		}

	}

}

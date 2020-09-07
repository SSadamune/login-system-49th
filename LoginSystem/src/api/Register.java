package api;

import jdbc.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
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
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		UserInf currentUser = new UserInf();

		currentUser.userId(Integer.parseInt(request.getParameter("userId")));
		currentUser.userPw(new String(request.getParameter("userPw").getBytes("ISO8859-1"),"UTF-8"));
		currentUser.userName(new String(request.getParameter("userName").getBytes("ISO8859-1"),"UTF-8"));
		currentUser.userDeptNo(Integer.parseInt(request.getParameter("userDeptNo")));
		currentUser.userRgstDate("2020-09-07");

		currentUser.insert();

	}

}

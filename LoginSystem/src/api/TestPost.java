package api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestPost
 */
@WebServlet("/TestPost")
public class TestPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestPost() {
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

		String userName = new String(request.getParameter("userName").getBytes("ISO8859-1"),"UTF-8");

		System.out.println("IDは" + request.getParameter("userId"));
		System.out.println("名前は" + userName);
		System.out.println("部署番号は" + request.getParameter("userDeptNo"));

		response.getWriter().append("user id is : ").append(request.getParameter("userId"));
	}

}

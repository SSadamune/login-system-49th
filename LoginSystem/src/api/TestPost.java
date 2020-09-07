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

		PrintWriter out = response.getWriter();
		String title = "マイページ";

		String userName = new String(request.getParameter("userName").getBytes("ISO8859-1"),"UTF-8");

		String docType = "<!DOCTYPE html> \n";
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title></head>\n" +
				"<body bgcolor=\"#f0f0f0\">\n" +
				"<h1 align=\"center\">" + title + "</h1>\n" +
				"<ul>\n" +
				"  <li><b>ユーザID</b>："
				+ request.getParameter("userId") + "\n" +
				"  <li><b>氏名</b>："
				+ userName + "\n" +
				"  <li><b>所属部署No</b>："
				+ request.getParameter("userDeptNo") + "\n" +
				"</ul>\n" +
				"</body></html>");
	}

}

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/helloworld")
public class ServletTest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String param1 = request.getParameter("param");
        
        response.getWriter().write("1.Hello "+ param1);
        
        response.getWriter().println("2.Hello "+ param1);

        PrintWriter out = response.getWriter();
        out.println("3.Hello "+ param1);
    }
}

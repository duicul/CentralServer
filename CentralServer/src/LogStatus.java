

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogStatus
 */
@WebServlet("/LogStatus")
public class LogStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp="";
		//System.out.println("Logstatus");
		response.setHeader("Content-type", "text/plain");
		HttpSession s=request.getSession();
		if(s==null||s.getAttribute("user")==null)
		{s=request.getSession(true);
		resp+="error";
		}
		else
		{resp+=s.getAttribute("user");}
		response.getWriter().append(resp);
	}


}

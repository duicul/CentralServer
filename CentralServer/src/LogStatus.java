

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp="";
		System.out.println("Logstatus");
		HttpSession s=request.getSession();
		resp+="<p>";
		if(s==null||s.getAttribute("user")==null)
		{s=request.getSession(true);
		resp+="<input type=\"text\" id=\"user_txt\" width=\"30\"></input><br>";
		resp+="<input type=\"password\" id=\"pass_txt\" width=\"30\"></input><br>";
		resp+="<button onclick=\"login()\">Login</button><br>";}
		else
		{resp+="Hello "+s.getAttribute("user")+"<br>";
		resp+="<button onclick=\"logout()\">Logout</button><br>";}
		resp+="</p>";
		response.getWriter().append(resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

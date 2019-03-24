

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
		resp+="<div>";
		if(s==null||s.getAttribute("user")==null)
		{s=request.getSession(true);
		//resp+="<div class=\"row\">";
		resp+="<form class=\"form-inline\" id=\"loginform\">";
		resp+="<div class=\"input-group\">";
		resp+=/*"<div class=\"col\">"+*/"<input type=\"text\" class=\"form-control\" placeholder=\"UserName\" id=\"user_txt\" width=\"30\" />"/*+"</div>"*/;
		resp+=/*"<div class=\"col\">"+*/"<input type=\"password\" class=\"form-control\" placeholder=\"Password\" id=\"pass_txt\" width=\"30\" />"/*+"</div>"*/;
		resp+=/*"<div class=\"col\">"+*/"<input type=\"submit\" class=\"btn btn-primary\" value=\"Login\" />"/*+"</div>"*/;
		resp+="</div></form>";
		}
		else
		{resp+="<div class=\"input-group\">";
		resp+="<font color=\"white\">Hello "+s.getAttribute("user")+"</font>";
		resp+="<button onclick=\"logout()\" class=\"btn btn-primary\">Logout</button>";
		resp+="</div>";}
		resp+="</div>";
		response.getWriter().append(resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

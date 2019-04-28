

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.MySqlData;
import data.ServerData;

/**
 * Servlet implementation class AddInputPin
 */
@WebServlet("/AddInputPin")
public class AddInputPin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInputPin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServerData sd=new MySqlData(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null)
		{int uid=sd.getUser(s.getAttribute("user").toString()).uid;
		sd.insertInputPin(Integer.parseInt(request.getParameter("pin").toString()),"0 0",request.getParameter("name").toString(),request.getParameter("sensor").toString(),uid);
		response.getWriter().append("okay");
		return;}
		response.getWriter().append("error");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

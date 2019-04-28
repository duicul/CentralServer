

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.MySqlData;
import data.PinInput;
import data.ServerData;

/**
 * Servlet implementation class InputPinLog
 */
@WebServlet("/InputPinLog")
public class InputPinLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputPinLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pin_no=Integer.parseInt(request.getParameter("pin").toString());
		System.out.println("Pin number "+pin_no);
		ServerData sd=new MySqlData(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null)
		{int uid=sd.getUser(s.getAttribute("user").toString()).uid;
		PinInput pi=sd.getIntputPinbyPin_no(pin_no,uid);
		response.getWriter().append(pi.drawGraph(uid));		
		}
		else  response.getWriter().append("error");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

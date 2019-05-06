

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.OutputPinData;
import data.OutputPinMySQL;
import data.PinOutput;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class OutputPinLog
 */
@WebServlet("/OutputPinLog")
public class OutputPinLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutputPinLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pin_no=Integer.parseInt(request.getParameter("pin").toString());
		System.out.println("Pin number "+pin_no);
		OutputPinData sdout=new OutputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null)
		{int uid=sd.getUser(s.getAttribute("user").toString()).uid;
		PinOutput po=sdout.getOutputPinbyPin_no(pin_no,uid);
		if(po!=null) {
		response.getWriter().append(po.getHelper(uid).drawGraph());		
		}
		}
		else  response.getWriter().append("error");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

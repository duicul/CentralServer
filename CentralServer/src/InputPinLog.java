

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.InputPinData;
import data.InputPinMySQL;
import data.Pin;
import data.User;
import data.UserData;
import data.UserMySQL;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pin_no=Integer.parseInt(request.getParameter("pin").toString());
		System.out.println("Pin number "+pin_no);
		InputPinData sdin=new InputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null)
		{User u=sd.getUser(s.getAttribute("user").toString());
		if(u!=null) {
				int uid=sd.getUser(s.getAttribute("user").toString()).uid;
				Pin pi=sdin.getIntputPinbyPin_no(pin_no,uid);
				if(pi!=null) {
					response.getWriter().append(pi.getHelper(uid).drawGraph());		
					return;}
		}}
		else  response.getWriter().append("error");
	}

}



import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.ConditionData;
import data.ConditionMySQL;
import data.DatabaseSetup;
import data.OutputPinData;
import data.OutputPinMySQL;
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class AddConditionOut
 */
@WebServlet("/AddConditionOut")
public class AddConditionOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddConditionOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConditionData sdcon=new ConditionMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		OutputPinData sdout=new OutputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		int pin=Integer.parseInt(request.getParameter("pin"));
		System.out.println(request.getParameter("time_start"));
		Time start=Time.valueOf(request.getParameter("time_start")+":00");//needs seconds
		Time end=Time.valueOf(request.getParameter("time_end")+":00");//needs seconds
		boolean val=Integer.parseInt(request.getParameter("val"))==1;
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser(s.getAttribute("user").toString());
			if(u!=null) {
				if(sdout.getOutputPinbyPin_no(pin, u.uid)==null) {
					response.getWriter().append("error");
					return;}
				sdcon.addConditionOut(u.uid, pin, start, end, val);
				response.getWriter().append("okay");
				return;
			}
		}
		response.getWriter().append("error");
	}

}



import java.io.IOException;
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
 * Servlet implementation class AddCondition
 */
@WebServlet("/AddCondition")
public class AddCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCondition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		response.getWriter().append("<div></div>");}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConditionData sdcon=new ConditionMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		OutputPinData sdout=new OutputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		//sdcon.addCondition(uid, pin_in, pin_out, cond, val);
		
		
		int pin_in=Integer.parseInt(request.getParameter("pin_in"));
		int pin_out=Integer.parseInt(request.getParameter("pin_out"));
		int val=Integer.parseInt(request.getParameter("val"));
		String cond=request.getParameter("cond");
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser(s.getAttribute("user").toString());
			if(u!=null) {
				if(sdout.getOutputPinbyPin_no(pin_out, u.uid)==null) {
					response.getWriter().append("error");
					return;}
				sdcon.addCondition(u.uid, pin_in, pin_out, cond, val==0?false:true);
				response.getWriter().append("okay");
				return;
			}
		}
		response.getWriter().append("error");
		
	}

}

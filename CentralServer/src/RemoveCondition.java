

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
import data.Pin;
import data.PinMySQL;
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class RemoveCondition
 */
@WebServlet("/RemoveCondition")
public class RemoveCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveCondition() {
        super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		ConditionData sdcon=new ConditionMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		try {
		int cid=Integer.parseInt(request.getParameter("cid").toString());
		int pin=Integer.parseInt(request.getParameter("pin").toString());
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser(s.getAttribute("user").toString());
			if(u!=null) {
				Pin p=new PinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass).getPin(pin, u.uid);
				if(p!=null)
					switch(p.type) {
					case "IN":sdcon.removeConditionIn(cid);response.getWriter().append("okay");return;
					case "OUT":sdcon.removeConditionOut(cid);response.getWriter().append("okay");return;
					}
			}
		}
		}catch(Exception e) {}
		response.getWriter().append("error");
	}
}

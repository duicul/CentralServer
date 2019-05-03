

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
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class RemoveCondition
 */
@WebServlet("/RemoveCondition")
public class RemoveCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCondition() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		ConditionData sdcon=new ConditionMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		int cid=Integer.parseInt(request.getParameter("cid").toString());
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser(s.getAttribute("user").toString());
			if(u!=null) {
				int uid=u.uid;
				sdcon.removeCondition(uid, cid);
				response.getWriter().append("okay");
				return;}
			}
		response.getWriter().append("error");
	}
}

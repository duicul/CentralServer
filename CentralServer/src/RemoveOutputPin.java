

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
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class RemoveOutputPin
 */
@WebServlet("/RemoveOutputPin")
public class RemoveOutputPin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveOutputPin() {
        super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputPinData sdout=new OutputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser(s.getAttribute("user").toString());
			if(u!=null) {
				int uid=u.uid;
				try {
				sdout.removeOutputPinbyPin_no(Integer.parseInt(request.getParameter("pin").toString()), uid);}
				catch(Exception e ) {response.getWriter().append("error");return;}
				response.getWriter().append("okay");
				return;}
		}
		response.getWriter().append("error");
	}

}

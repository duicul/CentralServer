

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
import data.User;
import data.UserData;
import data.UserMySQL;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputPinData sdin=new InputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null)
		{User u=sd.getUser(s.getAttribute("user").toString());
		if(u!=null){
			int uid=u.uid;
			try {
				sdin.insertInputPinNoLog(Integer.parseInt(request.getParameter("pin").toString()),"",request.getParameter("name").toString(),request.getParameter("sensor").toString(),uid);}
			catch(Exception e) {response.getWriter().append("error");return;}
			response.getWriter().append("okay");
			return;}
		}
		response.getWriter().append("error");
	}

}

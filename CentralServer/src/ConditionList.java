

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.HTMLHelper;
import data.InputPinData;
import data.InputPinMySQL;
import data.PinInput;
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class ConditionList
 */
@WebServlet("/ConditionList")
public class ConditionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConditionList() {
        super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pin_no=Integer.parseInt(request.getParameter("pin").toString());
		InputPinData sdin=new InputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		StringBuilder sb=new StringBuilder();
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser(s.getAttribute("user").toString());
			if(u==null) {
				return;}
			PinInput pi=sdin.getIntputPinbyPin_no(pin_no, u.uid);
			HTMLHelper hh=pi.getHelper(u.uid);
			sb.append(hh.getConditionList());
			sb.append("<hr>");
			sb.append(hh.getConditionForm());			
			response.getWriter().append(sb.toString());
		}
	}

}

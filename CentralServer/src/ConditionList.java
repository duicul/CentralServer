

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.Pin;
import data.PinData;
import data.PinMySQL;
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class ConditionList
 */
@WebServlet("/ConditionList")
public class ConditionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConditionList() {
        super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pin_no=-1;
		PinData sdpin=new PinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		StringBuilder sb=new StringBuilder();
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser(s.getAttribute("user").toString());
			if(u==null) {
				return;}
			try {
				pin_no=Integer.parseInt(request.getParameter("pin").toString());}
			catch(Exception e) {return;}
			Pin p=sdpin.getPin(pin_no, u.uid);
			if(p!=null) {
				//System.out.println("Condition List "+p);
				sb.append(p.getHelper(u.uid).getConditionList());
			}
			//System.out.println("ConditionList "+p);
			response.getWriter().append(sb.toString());
		}
	}

}



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
 * Servlet implementation class ConditionForm
 */
@WebServlet("/ConditionForm")
public class ConditionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConditionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pin_no=Integer.parseInt(request.getParameter("pin").toString());
		PinData sdpin=new PinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		StringBuilder sb=new StringBuilder();
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser(s.getAttribute("user").toString());
			if(u==null) {
				return;}
			Pin p=sdpin.getPin(pin_no, u.uid);
			
			if(p!=null) {
				//System.out.println("Condition Form "+p);
				sb.append(p.getHelper(u.uid).getConditionForm());
			}
			
			//System.out.println("ConditionForm "+p);
			response.getWriter().append(sb.toString());
		}
	}

}

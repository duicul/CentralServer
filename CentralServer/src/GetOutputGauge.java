

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
import data.PinOutput;
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class GetOutputGauge
 */
@WebServlet("/GetOutputGauge")
public class GetOutputGauge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOutputGauge() {
        super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputPinData sdin=new OutputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		try {int pin=Integer.parseInt(request.getParameter("pin"));
			if(s!=null&&s.getAttribute("user")!=null){
				User u=sd.getUser(s.getAttribute("user").toString());
				if(u==null)return;
				int uid=u.uid;
				PinOutput po=sdin.getOutputPinbyPin_no(pin, uid);
				if(po!=null){
					response.getWriter().append(po.getHelper(uid).getGauge());
					return;}
				return;}
		}catch(Exception e){
			e.printStackTrace();return;}
	}

}

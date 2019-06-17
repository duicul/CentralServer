

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
import data.Pin;
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class SensorGauges
 */
@WebServlet("/SensorGauges")
public class SensorGauges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SensorGauges() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		if(s==null||s.getAttribute("user")==null)
			return;
		InputPinData sd=new InputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData usd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		User u=usd.getUser(s.getAttribute("user").toString());
		if(u==null)return;
		int uid=u.uid;
		StringBuilder data=new StringBuilder();
		for(Pin po:sd.getPinsInput(uid)) {
			data.append(po.getHelper(uid).getGauge()+"<br/>");}
		response.getWriter().append(data);
	}

}

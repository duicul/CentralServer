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

@WebServlet("/OutputGauges")
public class OutputGauges extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OutputGauges() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputPinData sdout=new OutputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		StringBuilder resp=new StringBuilder();
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser(s.getAttribute("user").toString());
			if(u==null)return;
			int uid=u.uid;
			System.out.println("output gauges");
			for(PinOutput po:sdout.getPinsOutput(uid)) {
				resp.append("<div class=\"row\"><div class=\"col\" id=\"out_pin_"+po.pin_no+"\">");
				resp.append(po.getHelper(uid).getGauge());
				resp.append("</div></div><br/>");
				}
			response.getWriter().append(resp);}
	}
}

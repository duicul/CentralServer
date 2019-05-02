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
import data.Pin;
import data.PinData;
import data.PinMySQL;
import data.PinOutput;
import data.UserData;
import data.UserMySQL;

@WebServlet("/OutputPinsList")
public class OutputPinsList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OutputPinsList() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputPinData sdout=new OutputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		PinData sdpin=new PinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		StringBuilder resp=new StringBuilder();
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null)
		{int uid=sd.getUser(s.getAttribute("user").toString()).uid;
		//"<!DOCTYPE html><html>";
			for(PinOutput po:sdout.getPinsOutput(uid))
				{Pin p=sdpin.getPin(po.pin_no,uid);
				if(p!=null){
					resp.append("<div class=\"row\">");
					resp.append("<div class=\"col\">"+po.pin_no+" "+p.name+"</div>");
					resp.append("<div class=\"col\">"+"<button class=\"btn "+(po.value==0?"btn-secondary":"btn-warning")+" \" onclick=\"togglepin("+po.pin_no+")\">"+(po.value==0?"OFF":"ON")+"</button></div>");
					resp.append("<div class=\"col\">"+"<button class=\"btn btn-danger\" onclick=\"removeoutputpin("+po.pin_no+")\">"+"Remove "+p.name+"</button>"+"</div>");}
					resp.append("</div>");}
		}
		response.getWriter().append(resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}



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
 * Servlet implementation class ToggleOutputPin
 */
@WebServlet("/ToggleOutputPin")
public class ToggleOutputPin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ToggleOutputPin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		UserData usd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null){
			User u=usd.getUser(s.getAttribute("user").toString());
			if(u==null)return;
				int uid=u.uid;
			OutputPinData sd=new OutputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
			int pin_no=-1;
			try{
				pin_no=Integer.parseInt(request.getParameter("pin_no"));
			}catch(Exception e) {return;}
			sd.toggleOutputPin(pin_no,uid);
		}
	}

}

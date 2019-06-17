

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
import data.PIR;
import data.PinInput;
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class ToggleInputPin
 */
@WebServlet("/ToggleInputPin")
public class ToggleInputPin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToggleInputPin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/plain");
		HttpSession s=request.getSession();
		if(s!=null&&s.getAttribute("user")!=null){
			UserData usd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
			User u=usd.getUser(s.getAttribute("user").toString());
			if(u==null)return;
			int uid=u.uid;
			InputPinData sd=new InputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
			int pin_no=-1;
			try {
			pin_no=Integer.parseInt(request.getParameter("pin_no"));}
			catch(Exception e) {return;}
			PinInput pir=(PinInput) sd.getIntputPinbyPin_no(pin_no, uid);
			sd.updateInputPinValueNoLogNotimestamp(pin_no,pir.active?"0":"1", uid);}
	}
}

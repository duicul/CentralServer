

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
		//System.out.println("toggleoutputpin");
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		if(s!=null&&s.getAttribute("user")!=null)
		{int uid=(int) s.getAttribute("user_uid");
		OutputPinData sd=new OutputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		int pin_no=-1;
		pin_no=Integer.parseInt(request.getParameter("pin_no"));
		//System.out.println(pin_no);
		sd.toggleOutputPin(pin_no,uid);
		//response.getWriter().append("<div></div>");
		}
	}

}

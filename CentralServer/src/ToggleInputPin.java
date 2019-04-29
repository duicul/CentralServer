

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.MySqlData;
import data.PIR;
import data.ServerData;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("toggleoutputpin");
		response.setHeader("Content-type", "text/plain");
		HttpSession s=request.getSession();
		if(s!=null&&s.getAttribute("user")!=null){
			int uid=(int) s.getAttribute("user_uid");
			ServerData sd=new MySqlData(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
			int pin_no=-1;
			pin_no=Integer.parseInt(request.getParameter("pin_no"));
			//System.out.println(pin_no);
			PIR pir=(PIR)sd.getIntputPinbyPin_no(pin_no, uid);
			boolean val=pir.active;
			if(val)
			sd.updateInputPinValueLogNotimestamp(pin_no,"0", uid);
			else sd.updateInputPinValueNoLogNotimestamp(pin_no,"1", uid);
			//response.getWriter().append("<div></div>");
				}
	}
}

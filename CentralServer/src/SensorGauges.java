

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		if(s==null||s.getAttribute("user")==null)
			return;
		InputPinData sd=new InputPinMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		int uid=(int) s.getAttribute("user_uid");
		StringBuilder data=new StringBuilder();
		for(Pin po:sd.getPinsInput(uid)) {
			System.out.println(po.name);
			//data+="<div class=\"row\">";
			data.append(po.getHelper(uid).getGauge()+"<br/>");}
		//System.out.println(data);
		response.getWriter().append(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

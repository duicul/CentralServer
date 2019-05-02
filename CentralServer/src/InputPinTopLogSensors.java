

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.MySqlData;
import data.PinInput;
import data.ServerData;

/**
 * Servlet implementation class InputPinTopLogSensors
 */
@WebServlet("/InputPinTopLogSensors")
public class InputPinTopLogSensors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputPinTopLogSensors() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServerData sd=new MySqlData(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		response.setHeader("Content-type", "text/plain");
		List<String> sensors=new ArrayList<String>();
		sensors.add("PIR");
		//sensors.add("DHT");
		if(s!=null&&s.getAttribute("user")!=null)
		{int uid=sd.getUser(s.getAttribute("user").toString()).uid;
		List<PinInput> lpi=sd.getTopPinInputLogSensors(uid, sensors);
		if(lpi!=null)
		{StringBuilder data=new StringBuilder();
			for(PinInput pi:lpi)
				data.append(pi.name+" "+pi.sensor+" "+pi.timestamp+"<br/>");
			response.getWriter().append(data);		
		}}
		else  response.getWriter().append("error");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

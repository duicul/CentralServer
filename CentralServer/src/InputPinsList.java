

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MySqlData;
import data.Pin;
import data.PinInput;
import data.ServerData;

/**
 * Servlet implementation class InputPinsList
 */
@WebServlet("/InputPinsList")
public class InputPinsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputPinsList() {
        super(); }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServerData sd=new MySqlData(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		String resp="<div>";
		if(s!=null&&s.getAttribute("user")!=null){
			int uid=(int) s.getAttribute("user_uid");
			for(PinInput pi:sd.getPinsInput(uid)){
				Pin p=sd.getPin(pi.pin_no,uid);
				System.out.println("Sensor type |"+pi.sensor+"|");
				if(pi.sensor.equals("DHT11")||pi.sensor.equals("DHT22")){
					List<Double> vals=Arrays.asList(pi.value.split(" ")).stream().filter(strval -> strval.length()>0).map(realval -> Double.parseDouble(realval)).collect(Collectors.toList());
					System.out.println("Values "+vals.toString());
					if(p!=null){
						resp+="<p>"+pi.pin_no+" "+p.name+" "+p.type+" "+pi.sensor+" Temperature : "+vals.get(0)+"C Humidity : "+vals.get(1)+"% "+pi.timestamp+"</p>";
	       						}
				}
				else 
					if(p!=null)
						resp+="<p>"+pi.pin_no+" "+p.name+" "+p.type+" "+pi.sensor+" "+pi.value+" "+pi.timestamp+"</p>";
				}
		  }
		 resp+="</div>";
		 response.getWriter().append(resp);
	}
}

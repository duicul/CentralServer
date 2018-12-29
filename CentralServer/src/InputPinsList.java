

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String resp="";//"<!DOCTYPE html><html>";
			for(PinInput pi:sd.getPinsInput(1))
				{Pin p=sd.getPin(pi.pin_no,1);
				if(p!=null)
					resp+=pi.pin_no+" "+p.name+" "+p.type+" "+pi.sensor+" "+pi.value+" "+pi.timestamp+"<br>";}
		//resp+="</html>";
		response.getWriter().append(resp);
	}
}

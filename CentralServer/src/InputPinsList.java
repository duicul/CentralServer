

import java.io.IOException;
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
		if(s!=null&&s.getAttribute("user")!=null)
		{int uid=(int) s.getAttribute("user_uid");
		String resp="<span>";//"<!DOCTYPE html><html>";
			for(PinInput pi:sd.getPinsInput(uid))
				{Pin p=sd.getPin(pi.pin_no,uid);
				if(p!=null)
					resp+="<p>"+pi.pin_no+" "+p.name+" "+p.type+" "+pi.sensor+" "+pi.value+" "+pi.timestamp+"</p>";}
		resp+="</span>";
			//resp+="</html>";
		response.getWriter().append(resp);}
	}
}

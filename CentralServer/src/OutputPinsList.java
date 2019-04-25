import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.MySqlData;
import data.Pin;
import data.PinOutput;
import data.ServerData;

@WebServlet("/OutputPinsList")
public class OutputPinsList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OutputPinsList() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServerData sd=new MySqlData(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		String resp="<div>";
		if(s!=null&&s.getAttribute("user")!=null)
		{int uid=(int) s.getAttribute("user_uid");
		//"<!DOCTYPE html><html>";
			for(PinOutput po:sd.getPinsOutput(uid))
				{Pin p=sd.getPin(po.pin_no,uid);
				if(p!=null)
					resp+="<p>"+po.pin_no+" "+p.name+" "+p.type+" "+"<button onclick=\"togglepin("+po.pin_no+")\">"+(po.value==0?"OFF":"ON")+"</button>"+"</p>";}
		//resp+="</html>";
		}
		resp+="</div>";
		response.getWriter().append(resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

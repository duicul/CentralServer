import java.io.IOException;

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
			System.out.println(sd.getPinsInput(uid).size()+" input pins");
			for(PinInput pi:sd.getPinsInput(uid)){
				System.out.println("Sensor type |"+pi.sensor+"|");
				resp+=pi.getData();}}
		 resp+="</div>";
		 //System.out.println(resp);
		 response.getWriter().append(resp);
	}
}

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		String resp="";//"<!DOCTYPE html><html>";
			for(PinOutput po:sd.getPinsOutput())
				{Pin p=sd.getPin(po.pin_no);
				if(p!=null)
					resp+=po.pin_no+" "+p.name+" "+p.type+" "+"<button onclick=\"togglepin("+po.pin_no+")\">"+(po.value==0?"OFF":"ON")+"</button>"+"<br>";}
		//resp+="</html>";
		response.getWriter().append(resp);}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

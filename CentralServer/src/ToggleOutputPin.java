

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MySqlData;
import data.ServerData;

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
		System.out.println("toggleoutputpin");
		ServerData sd=new MySqlData(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		int pin_no=-1;
		pin_no=Integer.parseInt(request.getParameter("pin_no"));
		System.out.println(pin_no);
		sd.toggleOutputPin(pin_no);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}

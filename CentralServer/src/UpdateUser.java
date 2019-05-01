

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.MySqlData;
import data.ServerData;
import data.User;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServerData sd=new MySqlData(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		System.out.println("SignUp");
		HttpSession s=request.getSession();
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sd.getUser((String)s.getAttribute("user"));
			if(u==null)
				{response.getWriter().append("error");return;}
			String user=u.name;
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			String phone=request.getParameter("phone");
			String info=request.getParameter("info");
			response.setHeader("Content-type", "text/plain");   
		    if(sd.updateUser(user,email, address, phone, info)) {
		    	response.getWriter().append("okay");
		    	return;}
		}
		response.getWriter().append("error");
	}
}

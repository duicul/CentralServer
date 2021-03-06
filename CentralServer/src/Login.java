import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.User;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		System.out.println("Login");
		String user=request.getParameter("user");
		String pass=request.getParameter("password");
		HttpSession s=request.getSession(true);
		response.setHeader("Content-type", "text/plain");   
		System.out.println(request.getMethod()+" Logging "+user);
		if(user!=null&&pass!=null) {
		User u=sd.getUser(user, pass);
		if(u!=null){
			s.setAttribute("user", user);
			s.setAttribute("user_uid", u.uid);
			response.getWriter().append("okay");}
		return;
		}
		response.getWriter().append("error");
		//System.out.println("User "+s.getAttribute("user")+" logged in");
	}

}



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DatabaseSetup;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		if(s!=null&&s.getAttribute("user")!=null){
			String user=(String) s.getAttribute("user");
			String password=request.getParameter("password");
			String oldpassword=request.getParameter("oldpassword");
			response.setHeader("Content-type", "text/plain");   
		    if(user!=null&&password!=null&&oldpassword!=null&&sd.changePassword(user,oldpassword,password)) {
		    	response.getWriter().append("okay");
		    	return;}
		}
		response.getWriter().append("error");
	}

}

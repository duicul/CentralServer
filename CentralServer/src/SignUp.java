

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DatabaseSetup;
import data.UserData;
import data.UserMySQL;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserData sd=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		System.out.println("SignUp");
		String user=request.getParameter("user");
		String pass=request.getParameter("password");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String info=request.getParameter("info");
		response.setHeader("Content-type", "text/plain");   
		if(user!=null&&pass!=null) {
		if(email!=null&&address!=null&&phone!=null&&info!=null&&sd.signup(user, pass, email, address, phone, info)) {
		response.getWriter().append("okay");
		return;}
		}
		response.getWriter().append("error");}

}

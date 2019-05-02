

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
 * Servlet implementation class UserForm
 */
@WebServlet("/UserForm")
public class UserForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserForm() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/plain");
		UserData sduser=new UserMySQL(DatabaseSetup.dbname,DatabaseSetup.user,DatabaseSetup.pass);
		HttpSession s=request.getSession();
		if(s!=null&&s.getAttribute("user")!=null){
			User u=sduser.getUser((String)s.getAttribute("user"));
			if(u==null)
				return;
			StringBuilder data=new StringBuilder();
			data.append("<hr />");
			data.append("<form class=\"form-inline\" id=\"updatepasswordform\">");
			data.append("<div class=\"row\"><div class=\"col\">Old Password</div><div class=\"col\"><input type=\"password\" class=\"form-control\" placeholder=\"OldPassword\" id=\"oldpass_txt_update\" width=\"30\" /></div></div>");
			data.append("<div class=\"row\"><div class=\"col\">New Password</div><div class=\"col\"><input type=\"password\" class=\"form-control\" placeholder=\"NewPassword\" id=\"pass_txt_update\" width=\"30\" /></div></div>");
			data.append("<div class=\"row\"><div class=\"col\">Confirm Password</div><div class=\"col\"><input type=\"password\" class=\"form-control\" placeholder=\"ConfirmPassword\" id=\"pass_txt_update_confirm\" width=\"30\" /></div></div>");
			data.append("<div class=\"row\"><div class=\"col\"></div><div class=\"col\"><input type=\"submit\" class=\"btn btn-primary\" value=\"ChangePassword\" /></div></div>");
			data.append("</form>");
			data.append("<div id=\"change_password_msg\"></div>");
			data.append("<hr />");
			data.append("<form class=\"form-inline\" id=\"updateuserform\">");
		    data.append("<div class=\"row\"><div class=\"col\">E-mail</div><div class=\"col\"><input type=\"text\" class=\"form-control\" placeholder=\"E-mail\" id=\"email_txt_update\" width=\"30\" value=\""+u.email+"\"/></div></div>");
			data.append("<div class=\"row\"><div class=\"col\">Address</div><div class=\"col\"><input type=\"text\" class=\"form-control\" placeholder=\"Address\" id=\"address_txt_update\" width=\"30\" value=\""+u.adress+"\"/></div></div>");
			data.append("<div class=\"row\"><div class=\"col\">Phone</div><div class=\"col\"><input type=\"text\" class=\"form-control\" placeholder=\"Phone\" id=\"phone_txt_update\" width=\"30\" value=\""+u.phone+"\"/></div></div>");
			data.append("<div class=\"row\"><div class=\"col\">Info</div><div class=\"col\"><input type=\"text\" class=\"form-control\" placeholder=\"Info\" id=\"info_txt_update\" width=\"30\" value=\""+u.info+"\"/></div></div>");
			data.append("<div class=\"row\"><div class=\"col\"></div><div class=\"col\"><input type=\"submit\" class=\"btn btn-primary\" value=\"Update\" /></div></div>");
			data.append("</form>");
			data.append("<div id=\"update_user_msg\"></div>");
			data.append("<hr />");
			response.getWriter().append(data);
			return;
		}
		response.getWriter().append("error");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

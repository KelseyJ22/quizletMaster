package login;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet(name = "LoginServlet", urlPatterns = {"/login/LoginServlet"})
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountManager manager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        if(manager.isCorrectPassword(request.getParameter("name"), request.getParameter("password"))){
            RequestDispatcher nextPage = request.getRequestDispatcher("welcome.jsp");
            nextPage.forward(request, response);
        } else {
            RequestDispatcher nextPage = request.getRequestDispatcher("login_failure.jsp");
            nextPage.forward(request,response);
        }	
	}
}
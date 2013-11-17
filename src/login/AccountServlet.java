package login;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet(name = "AccountServlet", urlPatterns = {"/login/AccountServlet"})
public class AccountServlet extends HttpServlet {
    public AccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountManager manager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
		if (!manager.isUsernameTaken(request.getParameter("name"))) {
	            manager.createNewAccount(request.getParameter("name"), request.getParameter("password"));
	            RequestDispatcher nextPage = request.getRequestDispatcher("welcome.jsp");
	            nextPage.forward(request, response);
	        } else {
	            RequestDispatcher nextPage = request.getRequestDispatcher("create_account_collision.jsp");
	            nextPage.forward(request, response);
	        }
	    }
}

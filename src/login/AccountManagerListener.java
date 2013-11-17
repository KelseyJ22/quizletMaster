package login;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AccountManagerListener implements ServletContextListener {
    public AccountManagerListener() { }

    public void contextInitialized(ServletContextEvent arg0) {
        AccountManager manager = new AccountManager();
        ServletContext servlet = arg0.getServletContext();
        servlet.setAttribute(AccountManager.ATTRIBUTE_NAME, manager);    
    }

    public void contextDestroyed(ServletContextEvent arg0) { }
}
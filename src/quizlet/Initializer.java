package quizlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import database.MyDBInfo;
import database.SQLDatabase;
import database.SQLTable;

/**
 * Application Lifecycle Listener implementation class Initializer
 *
 */
@WebListener
public class Initializer implements ServletContextListener, HttpSessionListener 
{
    /**
     * Default constructor. 
     */
    public Initializer() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) 
    {
    	SQLDatabase database = new SQLDatabase(MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD, MyDBInfo.MYSQL_DATABASE_NAME);
    	QuizWebsite quizWebsite = QuizWebsite.loadFrom(database);
    	/*
    	QuizWebsite quizWebsite = new QuizWebsite();
    	//This has already been added to the database
    	
		List<Question> questions = new ArrayList<Question>();
		questions.add(new QuestionResponse("What class is this project for?", "CS108"));
		questions.add(new QuestionResponse("What school do we go to?", "Stanford"));
		//questions.add(new QuestionResponse("Is there a right answer to this question?", "Yes"));
		Quiz aQuiz = new Quiz("A Quiz", null, questions);
		quizWebsite.addQuiz(database, aQuiz);
		*/
		event.getServletContext().setAttribute(QuizWebsite.QUIZ_WEBSITE_ATTR, quizWebsite);
		event.getServletContext().setAttribute(QuizWebsite.SQL_DATABASE_ATTR, database);
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}

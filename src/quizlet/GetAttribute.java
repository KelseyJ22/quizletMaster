package quizlet;

import javax.servlet.ServletContext;

import database.SQLDatabase;

public class GetAttribute 
{
	static final String ATTR_QUIZ_WEBSITE = "QUIZ_WEBSITE"; 
	static final String ATTR_SQL_DATABASE = "SQL_DATABASE";
	
	//Use: QuizWebsite quizWebsite = GetAttribute.quizWebsiteFrom(getServletContext());
	public static QuizWebsite quizWebsiteFrom(ServletContext context)
	{
		return (QuizWebsite) context.getAttribute(ATTR_QUIZ_WEBSITE);
	}
	public static SQLDatabase sqlDatabaseFrom(ServletContext context)
	{
		return (SQLDatabase) context.getAttribute(ATTR_SQL_DATABASE);
	}
}

package quizlet;

import javax.servlet.ServletContext;

import database.SQLDatabase;

public class SetAttribute 
{
	public static void quizWebsiteTo(ServletContext context, QuizWebsite quizWebsite)
	{
		context.setAttribute(GetAttribute.ATTR_QUIZ_WEBSITE, quizWebsite);
	}
	public static void sqlDatabaseTo(ServletContext context, SQLDatabase database)
	{
		context.setAttribute(GetAttribute.ATTR_QUIZ_WEBSITE, database);
	}
}

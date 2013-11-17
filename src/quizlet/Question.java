package quizlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Contains sufficient data to represent a given Question and its answer
 * 
 * Publishes constants related to parameter passing in Servlets regarding questions
 * 
 * @author Seokho
 *
 */
public abstract class Question implements Serializable
{
	/** Use: getParameter(OPTION_PARAM) */
	public static final String OPTION_ATTR = "OPTION_ATTR";
	//JSP's will send OPTIONS as a parameter, while servlets will send them as attributes
	public static final String OPTION_PARAM = "OPTION_PARAM";
	/** Use: input name = "OPTION_EDIT" */
	public static final String OPTION_EDIT = "OPTION_EDIT";
	public static final String OPTION_QUESTION = "OPTION_QUESTION";
	public static final String OPTION_ANSWER = "OPTION_ANSWER";
	public static final String ANSWER_PARAM = "ANSWER_PARAM";
	
	//All stored in ServletContext
	public static final String QUIZ_ATTR = "QUIZ_ATTR";
	public static final String PREFERENCES_ATTR = "PREFERENCES_ATTR";
	public static final String QUESTIONS_LIST_ATTR = "QUESTIONS_LIST_ATTR";
	public static final String CURR_QUESTION_ATTR = "CURR_QUESTION_ATTR";
	public static final String PERFORMANCE_ATTR = "PERFORMANCE_ATTR";
	
	private static final long serialVersionUID = 1L;

	/** Constructs the page which asks the question 
	 *
	 *	Call the superclass method to make sure the page redirects correctly to the answer servlet 
	 *
	 * */
	
	//Name of the question to identify it with in the QuizSummary Page
	private String name; 				public String getName() { return name; }
	public Question(String name)
	{
		this.name = name;
	}
	
	protected void displayQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		request.getSession().setAttribute(Question.OPTION_ATTR, Question.OPTION_ANSWER);
	}
	
	//constructs the page which displays immediate feedback
	//Answer = String for now (will be in Performance)
	protected abstract void giveImmediateFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	protected abstract void displayEditPage(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	/** Add to the Performance Object */
	protected abstract void gradeAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException;
    
    /*
     * MUST override equals() and hashcode() !
     * 
     * They should work off of shallow comparisons: expect no deep copies of questions to exist
     * 
     */

}

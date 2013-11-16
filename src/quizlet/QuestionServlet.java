package quizlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public QuestionServlet() {
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Send in a parameter "OPTION" with one of the following values:
	 * EDIT (not built)
	 * QUESTION (displays the question)
	 * ANSWER (grades the answer to the question)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String option = request.getParameter(Question.OPTION_PARAM);
		Question question = (Question) request.getSession().getAttribute(Question.CURR_QUESTION_ATTR);
		QuizPreferences quizPrefs = (QuizPreferences) request.getSession().getAttribute(Question.PREFERENCES_ATTR);
		switch(option)
		{
			case Question.OPTION_EDIT : question.displayEditPage(request, response);
			case Question.OPTION_QUESTION : 
			{
				question.displayQuestion(request, response);
			}
			case Question.OPTION_ANSWER : 
			{
				if(quizPrefs.immediateFeedback())
				{
					question.giveImmediateFeedback(request, response);
				}
				else
				{
					question.gradeAnswer(request, response);
				}
				@SuppressWarnings("unchecked")
				List<Question> questions = (List<Question>) request.getSession().getAttribute(Question.QUESTIONS_LIST_ATTR);
				//does this edit questions in place (does the session context keep a pointer?)
				if(questions.isEmpty())
				{
					//We're done...
				}
				Question nextQuestion = questions.remove(0);
				//request.getSessionContext().setAttribute(QUESTIONS_LIST, questions);
				request.setAttribute(Question.OPTION_ATTR, Question.OPTION_QUESTION);
				request.setAttribute(Question.PERFORMANCE_ATTR, request.getAttribute(Question.PERFORMANCE_ATTR));
				request.setAttribute(Question.CURR_QUESTION_ATTR, nextQuestion);
				request.getServletContext().getRequestDispatcher("QuestionServlet").forward(request, response);
			}
		}
	}
}

package quizlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import profile.Profile;

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
	 * Send in an attribute "OPTION" with one of the following values:
	 * EDIT (not built)
	 * QUESTION (displays the question)
	 * ANSWER (grades the answer to the question)
	 * 
	 * If EDIT:
	 * 
	 * Else If QUESTION:
	 * Will display the question and prepare to send the answer back to this servlet. The OPTION attribute should be set to Question.ANSWER
	 * 
	 * Else IF ANSWER:
	 * Will either give immediate feedback, or will simply grade the answer. Question.CURR_QUESTION_ATTR and Question.QUESTIONS_LIST will
	 * be updated to reflect moving forward. If the quiz is complete, all Attributes related to this Quiz will be removed.
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String option = (String) request.getSession().getAttribute(Question.OPTION_ATTR);
		Question question = (Question) request.getSession().getAttribute(Question.CURR_QUESTION_ATTR);
		QuizPreferences quizPrefs = (QuizPreferences) request.getSession().getAttribute(Question.PREFERENCES_ATTR);
		switch(option)
		{
			case Question.OPTION_EDIT : question.displayEditPage(request, response); break;
			case Question.OPTION_QUESTION : 
			{
				question.displayQuestion(request, response); break;
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
					completeQuiz(request, response);
					return;
				}
				Question nextQuestion = questions.remove(0);
				request.getSession().setAttribute(Question.OPTION_ATTR, Question.OPTION_QUESTION);
				request.getSession().setAttribute(Question.CURR_QUESTION_ATTR, nextQuestion);
				request.getRequestDispatcher("/QuestionServlet").forward(request, response);
				break;
			}
		}
	}
	private void completeQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//add performance
		Profile profile = ((Profile) request.getSession().getAttribute(QuizWebsite.PROFILE_ATTR));
		Performance performance = ((Performance) request.getSession().getAttribute(Question.PERFORMANCE_ATTR));
		//profile.addPerformance(performance);
		//clear out unused attributes
		request.getSession().removeAttribute(Question.OPTION_ATTR);
		request.getSession().removeAttribute(Question.CURR_QUESTION_ATTR);
		request.getRequestDispatcher("QuizSummary.jsp").forward(request, response);
	}
}

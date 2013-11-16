package quizlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizServlet
 */
@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public QuizServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quiz quiz = (Quiz) request.getSession().getAttribute(Question.QUIZ_ATTR);
		List<Question> questions = quiz.getQuestions();
		Question firstQuestion = questions.remove(0);
		request.setAttribute(Question.QUESTIONS_LIST_ATTR, questions);
		request.setAttribute(Question.PERFORMANCE_ATTR, new Performance(quiz));
		request.setAttribute(Question.CURR_QUESTION_ATTR, firstQuestion);
		request.setAttribute(Question.OPTION_ATTR, Question.OPTION_QUESTION);
		request.getServletContext().getRequestDispatcher("QuestionServlet").forward(request, response);
		// TODO Auto-generated method stub
	}

}

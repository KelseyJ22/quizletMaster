package quizlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//This is a standard text question with an appropriate text response. 
//For example: Who was President during the Bay of Pigs fiasco?

/**
 * Servlet implementation class questionResponse
 */
@WebServlet("/questionResponse")
public class QuestionResponse extends Question {
	private static final long serialVersionUID = 1L;
    private String question;
    //for now we can assume there will only be something in index 0, but could be modified easily to have multiple correct options
    private ArrayList<String> answers;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionResponse(String name, String ques, String ans) {
        super(name);
        question = ques;
        answers = new ArrayList<String>();
        answers.add(ans);
    }
    
    public String correctAnswer(int i){
    	//error case
    	if (i > answers.size() - 1) return answers.get(0);
    	//correct functionality
    	else return answers.get(i);
    }
    
    public void addAnswer(String answer){
    	answers.add(answer);
    }
    
    public int numAnswers(){
    	return answers.size();
    }

	@Override
	protected void displayQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		super.displayQuestion(request, response);
		out.println("<head>");
		out.println("<title>Question</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(question);
		out.println("<form action=\"QuestionServlet\" method=\"post\">"
				+ "<input type=\"text\" name=\""+ Question.ANSWER_PARAM + "\" /><br><br>"
				+ "<input type=\"submit\" name=\"submit\" value = \"Next Question ->\"/>"
				+ "</p></form>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void giveImmediateFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String answer = request.getParameter(Question.ANSWER_PARAM);
		PrintWriter out = response.getWriter();
		out.println("<head>");
		out.println("<title>Question</title>");
		out.println("</head>");
		out.println("<body>");
		//default prints out the first answer in the list even if there are multiple options
		//TODO add some kind of formatting HTML here
		out.println(answers.get(0));
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void displayEditPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//deal with this later
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(question + " ");
		for(int i=0; i<answers.size(); i++){
			sb.append(answers.get(i));
		}
		return sb.toString();
	}

	@Override
	protected void gradeAnswer(HttpServletRequest request, HttpServletResponse response) 
	{
		//just getting something to work here
		String answer = request.getParameter(Question.ANSWER_PARAM);
		Performance performance = (Performance) request.getSession().getAttribute(Question.PERFORMANCE_ATTR);
		double score = 0.0d;
		if(answers.contains(answer))
		{
			score = 1.0d;
		}
		performance.addAnswer((Question) request.getSession().getAttribute(Question.CURR_QUESTION_ATTR), answer, score);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof QuestionResponse)
		{
			QuestionResponse other = (QuestionResponse) o;
			return other.question.equals(question) && other.answers.equals(answers);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return question.hashCode() * answers.hashCode();
	}
}

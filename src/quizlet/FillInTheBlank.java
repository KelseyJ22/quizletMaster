package quizlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//This is similar to standard Question-Response, except a blank can go 
//anywhere within a question. For example: One of President Lincoln's most famous 
//speeches was the __________ Address. 


/**
 * Servlet implementation class fillinTheBlank
 */
@WebServlet("/fillInTheBlank")
public class FillInTheBlank extends Question {
	private static final long serialVersionUID = 1L;
    private String question;
    //for now will assume only something at index 0, but could easily extend to have multiple correct answers
    private ArrayList<String> answers;
    
    //this is for display -- concatenates the answer into the question
    //maybe not needed?
    private String fullAnswer;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillInTheBlank(String name, String ques, String ans) {
        super(name);
        question = ques;
        answers = new ArrayList<String>();
        answers.add(ans);
        
        //TODO is this necessary?
        //fill fullAnswer with the question and its blank filled in with the answer
        int j=0;
        for(int i=0; i<ques.length(); i++){
        	if(ques.charAt(i)!='_') {
        		fullAnswer += ques.charAt(i);
        	}
        	else{
        		j++;
        		fullAnswer += ans.charAt(j);
        	}
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void displayQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<head>");
		out.println("<title>Question</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"QuestionServlet\" method=\"post\">"
				+ "<p>"+ question +".<br><br>"
				+ "<input type=\"text\" name=\"answer\" /><br><br>"
				+ "<input type=\"submit\" name=\"" + Question.ANSWER_PARAM + "\" value = \"Next Question ->\"/>"
				+ "<input name = \""+Question.OPTION_PARAM+"\" type = \"hidden\" value = \""+Question.OPTION_ANSWER+"\" />"
				+ "</p></form>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void giveImmediateFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String answer = (String) request.getParameter(Question.ANSWER_PARAM);
		PrintWriter out = response.getWriter();
		out.println("<head>");
		out.println("<title>Question</title>");
		out.println("</head>");
		out.println("<body>");
		//default prints out the first answer in the list even if there are multiple options
		//TODO add formatting HTML
		out.println(answers.get(0));
		out.println("</body>");
		out.println("</html>");
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
	protected void displayEditPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//deal with this later
	}

	@Override
	protected void gradeAnswer(HttpServletRequest request, HttpServletResponse response) 
	{
		Performance performance = (Performance) request.getSession().getAttribute(Question.PERFORMANCE_ATTR);
		Question currQuestion = (Question) request.getSession().getAttribute(Question.CURR_QUESTION_ATTR);
		String answer = request.getParameter(Question.ANSWER_PARAM);
		performance.addAnswer(currQuestion, answer, answers.contains(answer) ? 1 : 0);
	}
}

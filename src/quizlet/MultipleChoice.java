package quizlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Allow the user to select from one of a number of possible provided 
//answers. Please present multiple-choice questions using radio buttons this should 
//not be treated as a Question-Response question where the user enters an A, B, or 
//C into a blank textfield. 


/**
 * Servlet implementation class multipleChoice
 */
@WebServlet("/MultipleChoice")
public class MultipleChoice extends Question {
	private static final long serialVersionUID = 1L;
    private String question;
    private int correctIndex;
    private ArrayList<String> options;
    //WHYYY would anyone need this many options...? this mult choice would actually be hard!!
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultipleChoice(String name, String ques, int correct) {
        super(name);
        question = ques;
        correctIndex = correct;
        //will have to populate this with the options -- because
        //it's an ArrayList will allow for a variety of number of options.
        //(need to check that we allow for this when displayed)
        //ALSO should this be in the constructor or a required method to call later?
        //must balance simplicity at the creation vs during use/after initialization...
        options = new ArrayList<String>();
    }
    
    //add an answer option to the end of the options list
    public void addAnswer(String option){
    	options.add(option);
    }
    
    //add an answer option to a specified index in the options list
    public void addAnswer(int index, String option){
    	options.add(index, option);
    }
    
    //for checking the construction of a question
    public String showAnswers(){
    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<options.size(); i++){
    		sb.append(options.get(i));
    		sb.append("; ");
    	}
    	sb.append("correct: ");
    	sb.append(options.get(correctIndex) + " ");
    	sb.append(correctIndex);
    	
    	return sb.toString();
    }
    
    public String correctAnswer(){
    	return options.get(correctIndex);
    }


	@Override
	protected void displayQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<head>");
		out.println("<title>Question</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(question);
		out.println("<form action=\"QuestionServlet\" method=\"post\">"
				+ "<input type=\"text\" name=\"answer\" /><br><br>"
				+ "	<div align=\"center\"><br>");
		for(int i=0; i<options.size(); i++){
			out.println("<input type=\"radio\" name=\"options\" value=\"" + options.get(i) + "\"> " + options.get(i) + "<br>\"");
		}
		String selected = request.getParameter("options");
		out.println("</div>"
				+ "<input name = \" " + Question.ANSWER_PARAM + "\" type = \"hidden\" value = \"" + selected + "\" />"
				+ "<input name = \"" + Question.OPTION_PARAM + "\" type = \"hidden\" value = \"" + Question.OPTION_ANSWER+"\" />"
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
		//TODO add some kind of formatting HTML here
		out.println(alphabet.charAt(correctIndex));
		out.println(options.get(correctIndex));
		out.println();
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
		for(int i=0; i<options.size(); i++){
			sb.append(options.get(i));
		}
		sb.append(correctIndex);
		return sb.toString();
	}

	@Override
	protected void gradeAnswer(HttpServletRequest request, HttpServletResponse response) {
		//deal with this later
	}
}

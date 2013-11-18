package quizlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//In a picture response question, the system will display an 
//image, and the user will provide a text response to the image. Here are some 
//examples of picture-response questions. The system displays an image of a bird, the 
//user responds with the name of the bird species; the system displays an image of a 
//US President, the user responds with the name of the president; the system displays a 
//chemical structure of a molecule, the user responds with the name of the molecule. 

//To keep things simple, you may use absolute URLs to external images as the source 
//of your images, instead of allowing the user to upload images to your server when 
//creating a picture-response questions. For example a quiz on buildings at Stanford 
//would serve up: 
//http://events.stanford.edu/events/252/25201/Memchu_small.jpg 
//rather than serving up a copy of the image stored on your test server. 


/**
 * Servlet implementation class pictureResponse
 */
@WebServlet("/pictureResponse")
public class PictureResponse extends Question {
	private static final long serialVersionUID = 1L;
	private String link;
    //for now will assume only something at index 0, but could easily extend to have multiple correct answers
	private ArrayList<String> answers;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PictureResponse(String name, String url, String ans) {
        super(name);
        answers = new ArrayList<String>();
        link = url;
        answers.add(ans);
    }

	@Override
	protected void displayQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<head>");
		out.println("<title>Question</title>");
		out.println("</head>");
		out.println("<body>");
		//can we just display the link or do we need to somehow load the link's result in our browser?
		out.println(link);
		out.println("<form action=\"QuestionServlet\" method=\"post\">"
				+ "<input type=\"text\" name=\"answer\" /><br><br>"
				+ "<input type=\"submit\" name=\"" + Question.ANSWER_PARAM + "\" value = \"Next Question ->\"/>"
				+ "<input name = \""+Question.OPTION_PARAM+"\" type = \"hidden\" value = \""+Question.OPTION_ANSWER+"\" />"
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
		sb.append(link + " ");
		for(int i=0; i<answers.size(); i++){
			sb.append(answers.get(i));
		}
		return sb.toString();
	}

	@Override
	protected void gradeAnswer(HttpServletRequest request, HttpServletResponse response) {
		//deal with this later
	}
}

package quizlet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Performance implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4510851699752539718L;
	//What quiz is this Performance with respect to?
	private Quiz quiz;
	private List<Question> questions; //just to keep a reference
	private List<Answer> answers = new ArrayList<Answer>();
	public Performance(Quiz quiz)
	{
		this.quiz = quiz;
		this.questions = quiz.getQuestions();
	}
	/** Submits a score for this question */
	public void addAnswer(Question question, String answer, double score)
	{
		int index = questions.indexOf(question);
		if(index == -1)
		{
			System.err.println("Recording answer for a question not in quiz!");
		}
		answers.add(new Answer(question, answer, score));
	}
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for(Answer a : answers)
		{
			builder.append(a);
			builder.append("\n");
		}
		return builder.toString();
	}
	public double getTotalScore()
	{
		double totalScore = 0;
		for(Answer a : answers)
		{
			totalScore += a.getScore();
		}
		return totalScore;
	}
}
class Answer implements Serializable
{
	private Question question;
	private String answer;
	private double score;				public double getScore() { return score; }				
	
	Answer(Question question, String answer, double score)
	{
		this.question = question;
		this.answer = answer;
		this.score = score;
	}
	public String toString()
	{
		return question.getName()+" "+answer+" "+score;
	}
}

package quizlet;

import java.io.Serializable;
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
	private double[] scores;
	public Performance(Quiz quiz)
	{
		this.quiz = quiz;
		this.questions = quiz.getQuestions();
		scores = new double[questions.size()];
	}
	/** Submits a score for this question */
	public void addAnswer(Question question, double score)
	{
		int index = questions.indexOf(question);
		if(index == -1)
		{
			System.err.println("Recording answer for a question not in quiz!");
		}
		scores[index] = score;
	}
	public String toString()
	{
		double totalScore = 0;
		for(double points : scores)
		{
			totalScore += points;
		}
		return Double.toString(totalScore);
	}
}

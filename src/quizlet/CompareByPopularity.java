package quizlet;

import java.util.Comparator;

public class CompareByPopularity implements Comparator<Quiz> 
{
	@Override
	public int compare(Quiz a, Quiz b){
		return b.getTimesTaken() - a.getTimesTaken();
	}
}

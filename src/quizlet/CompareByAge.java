package quizlet;

import java.util.Comparator;

public class CompareByAge implements Comparator<Quiz> 
{
	@Override
	public int compare(Quiz a, Quiz b){
		//a is older than b
		return (int) (b.getCreationTime() - a.getCreationTime());
	}
}

package quizlet;

import java.util.Comparator;

public class Quiz implements Storable {
	private ArrayList<Question> allQuestions;
	private long creation;
	private int timesTaken;
	//public so can be updated outside the class
	//(this is assuming we don't make the QuizHistory method it's own thing)
	public ArrayList<Performance> history;
	public Map<Profile, Int> allRatings;
	public Map<Profile, String> allReviews;

	public Quiz() {
		//initialize all the instance variables
		allQuestions = new ArrayList<Question>();
		creation = System.currentTimeMillis();
		timesTaken = 0;
		history = new ArrayList<Performance>();
		allRatings = new Map<Profile, Int>();
		allReviews = new Map<Profile, String>();
	}
	
	/**
	 * Do something with the Storable interface...?
	 */
	
	private long creationTime(){
		return creation;
	}
	
	private int timesTaken(){
		return timesTaken;
	}
	
	public List<Question> getQuestions(){
		return allQuestions;
	}
	
	public Question questionAfter(Question current){
		int curr = allQuestions.indexOf(current);
		if(curr != allQuestions.length() - 1){
			return allQuestions.get(curr + 1);
		}
		//error result -- should never happen if we only call this after isLastQuestion gives the okay
		else return current;
	}
	
	public Boolean isLastQuestion(Question current){
		int curr = allQuestions.indexOf(current);
		return curr == allQuestions.length();
	}
	
	public void take(Profile){
		timesTaken++;
	}
	
	private Comparator<Quiz> compareByAge = new Comparator<Quiz>(){
		private int compare(Quiz a, Quiz b){
			//a is older than b
			if(a.creation < b.creation) return 1;
			//a is new than b
			if(a.creation > b.creation) return -1;
			//if a and b are the same age -- shouldn't happen (?)
			else return 0;
		}
	};
	
	private Comparator<Quiz> compareByPopularity = new Comparator<Quiz>(){
		private int compare(Quiz a, Quiz b){
			//a has been taken more times than b
			if(a.timesTaken > b.timesTaken) return 1;
			//a has been taken more times than b
			if(a.timesTaken < b.timesTaken) return -1;
			//a and b have been taken the same number of times
			else return 0;
		}
	}
	
	//in order for this to work must update history outside the class (quiz.history.add(...) etc)
	private List<Performance> quizHistory(){
		return history;
	}
	
	//in order for this to work must update this outside the class (quiz.allRatings.put(...) etc)
	private Map<Profile, Integer> rating(){
		return allRatings;
	}
	
	//in order for this to work must update this outside the class (quiz.allReviews.put(...) etc)
	private Map<Profile, String> review(){
		return allReviews;
	}

}

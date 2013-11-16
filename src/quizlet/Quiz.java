package quizlet;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.*;

public class Quiz implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9156629140836471846L;
	
	private List<Question> allQuestions;
	
	private String quizName;								public String getQuizName() { return quizName; }
	private Profile creator;								public Profile getCreator() { return creator; }
	
	private long creation = System.currentTimeMillis();		public long getCreationTime() { return creation; }
	private int timesTaken = 0;								public int getTimesTaken() { return timesTaken; }
	
	private List<Performance> history;
	//public so can be updated outside the class
	//(this is assuming we don't make QuizHistory it's own thing)
	
	//public ArrayList<Performance> history;
	private Map<Profile, Integer> allRatings = new HashMap<>();
	private Map<Profile, String> allReviews = new HashMap<>();

	public Quiz(String quizName, Profile creator, List<Question> questions) 
	{
		//initialize all the instance variables
		allQuestions = questions;
		this.quizName = quizName;
		this.creator = creator;
	}
	
	/** Returns a duplicated list of questions */
	public List<Question> getQuestions()
	{
		List<Question> newQuestions = new ArrayList<>();
		newQuestions.addAll(allQuestions);
		return newQuestions;
	}
	/*
	public Question questionAfter(Question current){
		int curr = allQuestions.indexOf(current);
		if(curr != allQuestions.size() - 1){
=======
import java.util.Comparator;
import database.Storable;
import java.util.ArrayList;

public class Quiz implements Storable {
	private ArrayList<Question> allQuestions;
	private long creation;
	private int timesTaken;
	//public so can be updated outside the class
	//(this is assuming we don't make the QuizHistory method it's own thing)
	public Map<Profile, String> allReviews;
	public Map<Profile, Integer> allRatings;

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
	
	@Override
	public String toSQL() {
		// TODO Auto-generated method stub
		return null;
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
>>>>>>> upstream/master
			return allQuestions.get(curr + 1);
		}
		//error result -- should never happen if we only call this after isLastQuestion gives the okay
		else return current;
	}
	
	public Boolean isLastQuestion(Question current){
		int curr = allQuestions.indexOf(current);
<<<<<<< HEAD
		return curr == allQuestions.size() - 1;
	}
	*/
	//hmmm what is this supposed to do?
	public void take(Profile profile){
		//aaaand something else
=======
		return curr == allQuestions.length();
	}
	
	public void take(Profile){
>>>>>>> upstream/master
		timesTaken++;
	}
	
	private Comparator<Quiz> compareByAge = new Comparator<Quiz>(){
<<<<<<< HEAD
		@Override
		public int compare(Quiz a, Quiz b){
			//a is older than b
			return (int) (b.creation - a.creation);
=======
		private int compare(Quiz a, Quiz b){
			//a is older than b
			if(a.creation < b.creation) return 1;
			//a is new than b
			if(a.creation > b.creation) return -1;
			//if a and b are the same age -- shouldn't happen (?)
			else return 0;
>>>>>>> upstream/master
		}
	};
	
	private Comparator<Quiz> compareByPopularity = new Comparator<Quiz>(){
<<<<<<< HEAD
		@Override
		public int compare(Quiz a, Quiz b){
			return b.timesTaken - a.timesTaken;
		}
	};
=======
		private int compare(Quiz a, Quiz b){
			//a has been taken more times than b
			if(a.timesTaken > b.timesTaken) return 1;
			//a has been taken more times than b
			if(a.timesTaken < b.timesTaken) return -1;
			//a and b have been taken the same number of times
			else return 0;
		}
	}
>>>>>>> upstream/master
	
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
<<<<<<< HEAD
=======

>>>>>>> upstream/master
}

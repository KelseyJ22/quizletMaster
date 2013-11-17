package quizlet;

import java.io.Serializable;
import java.util.*;

import profile.Profile;

public class Quiz implements Serializable 
{
	//Hardcoded sample quiz
	public static Quiz aQuiz;
	
	static
	{
		List<Question> questions = new ArrayList<Question>();
		questions.add(new QuestionResponse("What class is this project for?", "CS108"));
		questions.add(new QuestionResponse("What school do we go to?", "Stanford"));
		//questions.add(new QuestionResponse("Is there a right answer to this question?", "Yes"));
		aQuiz = new Quiz("A Quiz", null, questions);
	}
	
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
	
	
	private long creationTime(){
		return creation;
	}
	
	private int timesTaken(){
		return timesTaken;
	}
	
	/** Returns a shallow copy of questions */
	public List<Question> getQuestions()
	{
		List<Question> shallowCopy = new ArrayList<>();
		shallowCopy.addAll(allQuestions);
		return shallowCopy;
	}
	
	public Question questionAfter(Question current){
		int curr = allQuestions.indexOf(current);
		if(curr != allQuestions.size() - 1){
			return allQuestions.get(curr + 1);
		}
		//error result -- should never happen if we only call this after isLastQuestion gives the okay
		else return current;
	}
	
	public Boolean isLastQuestion(Question current){
		int curr = allQuestions.indexOf(current);
		return curr == allQuestions.size() - 1;
	}
	
	//hmmm what is this supposed to do?
	public void take(Profile profile){
		//aaaand something else
		timesTaken++;
	}
		
	private Comparator<Quiz> compareByAge = new Comparator<Quiz>(){
		@Override
		public int compare(Quiz a, Quiz b){
			//a is older than b
			return (int) (b.creation - a.creation);
		}
	};
	
	private Comparator<Quiz> compareByPopularity = new Comparator<Quiz>(){
		@Override
		public int compare(Quiz a, Quiz b){
			return b.timesTaken - a.timesTaken;
		}
	};
	
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

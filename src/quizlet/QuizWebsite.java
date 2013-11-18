package quizlet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import profile.Profile;
import database.SQLTable;
import database.SQLDatabase;

public class QuizWebsite implements Serializable
{
	private static final long serialVersionUID = -3603951536318213219L;
	
	private static final String TABLE = "QUIZ_WEBSITE_TABLE";
	private static final String ENTRY_NAME = "THE_ONE_QUIZ_WEBSITE";
	
	public static final String QUIZ_WEBSITE_ATTR = "QUIZ_WEBSITE"; 
	public static final String SQL_DATABASE_ATTR = "SQL_DATABASE";
	public static final String PROFILE_ATTR = "PROFILE_ATTR";
	
	//private List<Announcement> announcements = new ArrayList<>();			public List<Announcement> getAnnouncements() { return announcements; }
	//Quizzes hashed to their names
	private Map<String, Quiz> quizzes = new HashMap<String, Quiz>();		public Quiz getQuiz(String name) { return quizzes.get(name); } 
	private Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	public static QuizWebsite loadFrom(SQLDatabase database)
	{
		SQLTable<QuizWebsite> table = database.getTable(TABLE, QuizWebsite.class);
		return table.get(ENTRY_NAME);
	}
	
	/** Returns a list of the most popular quizzes in descending order of popularity */
	public List<Quiz> getPopularQuizzes()
	{
		//Not implemented yet
		return null;
	}
	/** Returns a list of the most recently created quizzes starting from the most recently created quiz */
	public List<Quiz> getRecentQuizzes()
	{
		//Not implemented yet
		return null;
	}
	
	public int getNumProfiles()
	{
		return profiles.values().size();
	}
	
	public int getNumQuizzes()
	{
		return quizzes.values().size();
	}
	/** Adds the quiz to the database. Saves the database. */
	public void addQuiz(SQLDatabase database, Quiz quiz)
	{
		quizzes.put(quiz.getQuizName(), quiz);
		save(database);
	}
	/** Adds the profile to the database. Saves the database. */
	public void addProfile(SQLDatabase database, Profile profile)
	{
		profiles.put(profile.getName(), profile);
		save(database);
	}
	/** Returns the profile by name. If no such profile exists, that's your problem. */
	public Profile getProfile(String name)
	{
		if(profiles.containsKey(name))
		{
			System.err.println("No profile named "+name);
			System.exit(1);
		}
		return profiles.get(name);
	}
	private void save(SQLDatabase database)
	{
		SQLTable<QuizWebsite> table = database.getTable(TABLE, QuizWebsite.class);
		table.put(ENTRY_NAME, this);
	}
}

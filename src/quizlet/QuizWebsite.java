<<<<<<< HEAD
package quizlet;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.SQLTable;
import database.Serializer;
import database.SQLDatabase;
import database.SQLUtils;

public class QuizWebsite implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3603951536318213219L;
	
	private static final String TABLE = "QUIZ_WEBSITE_TABLE";
	private static final String ENTRY_NAME = "THE_ONE_QUIZ_WEBSITE";
	
	private List<Announcement> announcements = new ArrayList<>();			public List<Announcement> getAnnouncements() { return announcements; }
	//Quizzes hashed to their names
	private Map<String, Quiz> quizzes = new HashMap<String, Quiz>();		public Quiz getQuiz(String name) { return quizzes.get(name); } 
	private Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	private SQLDatabase database;
	
	public QuizWebsite(SQLDatabase database)
	{
		this.database = database;
	}
	
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
	public void addQuiz(Quiz quiz)
	{
		quizzes.put(quiz.getQuizName(), quiz);
		save();
	}
	
	/** Adds the profile to the database. Saves the database. */
	public void addProfile(Profile profile)
	{
		profiles.put(profile.getName(), profile);
		save();
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
	
	private void save()
	{
		SQLTable<QuizWebsite> table = database.getTable(TABLE, QuizWebsite.class);
		table.put(ENTRY_NAME, this);
	}
	
}
=======
package quizlet;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.SQLTable;
import database.Serializer;
import database.SQLDatabase;
import database.SQLUtils;

public class QuizWebsite implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3603951536318213219L;
	
	private static final String TABLE = "QUIZ_WEBSITE_TABLE";
	private static final String ENTRY_NAME = "THE_ONE_QUIZ_WEBSITE";
	
	private List<Announcement> announcements = new ArrayList<>();			public List<Announcement> getAnnouncements() { return announcements; }
	//Quizzes hashed to their names
	private Map<String, Quiz> quizzes = new HashMap<String, Quiz>();		public Quiz getQuiz(String name) { return quizzes.get(name); } 
	private Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	private SQLDatabase database;
	
	public QuizWebsite(SQLDatabase database)
	{
		this.database = database;
	}
	
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
	public void addQuiz(Quiz quiz)
	{
		quizzes.put(quiz.getName(), quiz);
		save();
	}
	
	/** Adds the profile to the database. Saves the database. */
	public void addProfile(Profile profile)
	{
		profiles.put(profile.getName(), profile);
		save();
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
	
	private void save()
	{
		SQLTable<QuizWebsite> table = database.getTable(TABLE, QuizWebsite.class);
		table.put(ENTRY_NAME, this);
	}
	
}
>>>>>>> upstream/master

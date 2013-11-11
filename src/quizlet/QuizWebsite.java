package quizlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.BlobManager;
import database.SQLDatabase;
import database.SQLUtils;
import database.Storable;

public class QuizWebsite
{
	private static final String COLLECTIONS_DELIM = "-COLLECTIONS_DELIM-";
	private static final BlobManager BLOB_MGR = new BlobManager(COLLECTIONS_DELIM);
	
	private static final String COL_ANNOUNCEMENTS = "COL_ANNOUNCEMENTS";
	private static final String COL_QUIZZES = "COL_QUIZZES";
	private static final String COL_PROFILES = "COL_PROFILES";
	
	private static final String TABLE = "QUIZ_WEBSITE_TABLE";
	
	private List<Announcement> announcements = new ArrayList<>();			public List<Announcement> getAnnouncements() { return announcements; }
	//Quizzes hashed to their names
	private Map<String, Quiz> quizzes = new HashMap<String, Quiz>();		public Quiz getQuiz(String name) { return quizzes.get(name); } 
	private Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	private SQLDatabase database;
	
	public QuizWebsite(SQLDatabase database)
	{
		this.database = database;
		load();
	}
	
	/** Returns the most popular quizzes in descending order of popularity */
	public List<Quiz> getPopularQuizzes()
	{
		//Not implemented yet
		return null;
	}
	
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
	
	//Call once to reconfigure the table for this class
	public static void makeTable(SQLDatabase database)
	{
		database.executeUpdate("drop table "+TABLE);
		database.executeUpdate("create table "+TABLE+"("
				+ COL_ANNOUNCEMENTS + " blob,"
				+ COL_QUIZZES + " blob,"
				+ COL_PROFILES + " blob);");
	}
	
	private void load()
	{
		ResultSet rs = database.executeQuery("select * from "+TABLE);
		try
		{
			if(rs.next()) //only one entry expected
			{
				decompressAnnouncements(BLOB_MGR.decompressMap(rs.getString(COL_ANNOUNCEMENTS)));
				decompressProfiles(BLOB_MGR.decompressMap(rs.getString(COL_PROFILES)));
				decompressQuizzes(BLOB_MGR.decompressMap(rs.getString(COL_QUIZZES)));
			}
			else
			{
				System.out.println("No QuizWebsite in database");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}	
	
	private void decompressAnnouncements(Map<String, String> map)
	{
		for(String key : map.keySet())
		{
			announcements.put(key, new Announcement(map.get(key)));
		}
	}
	
	private void decompressProfiles(Map<String, String> map)
	{
		for(String key : map.keySet())
		{
			profiles.put(key, new Profile(map.get(key)));
		}
	}
	
	private void decompressQuizzes(Map<String, String> map)
	{
		for(String key : map.keySet())
		{
			quizzes.put(key, new Quiz(map.get(key)));
		}
	}
	
	private void save()
	{
		database.executeUpdate(sqlSaveString());
	}
	
	private String sqlSaveString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("insert into ");
		builder.append(TABLE);
		builder.append(" values(");
		builder.append(SQLUtils.format(BLOB_MGR.compress(announcements)) + ",");
		builder.append(SQLUtils.format(BLOB_MGR.compress(profiles.keySet())) + ",");
		builder.append(SQLUtils.format(BLOB_MGR.compress(quizzes.keySet())));
		builder.append(");");
		return builder.toString();

	}

}

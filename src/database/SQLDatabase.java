package database;

import java.sql.*;

public class SQLDatabase 
{
	static
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private Connection con;
	private Statement statement;
	
	/** Connects to a database server and opens the specified database */
	public SQLDatabase(String databaseServer, String username, String password, String databaseName)
	{			
		try
		{
			con = DriverManager.getConnection(
					"jdbc:mysql://" + databaseServer,
					username,
					password);
			statement = con.createStatement();
			statement.executeQuery("USE " + databaseName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Shutdown hook code from: http://stackoverflow.com/questions/5824049/running-a-method-when-closing-the-program
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() 
		{
	        public void run()
	        {
	        	try
	        	{
	        		con.close(); //make sure to close the connection
	        	}
	        	catch(Exception e)
	        	{
	        		e.printStackTrace();
	        	}
	        }
	    }, "Shutdown MySQL thread"));
	}

	public synchronized void executeUpdate(String request)
	{
		try
		{
			statement.executeUpdate(request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	public synchronized ResultSet executeQuery(String request)
	{
		try
		{
			return statement.executeQuery(request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		//not reached
		return null;
	}
}

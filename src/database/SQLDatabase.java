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
	
	private Connection con;				Connection getConnection() { return con; }
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
	/**
	 * 
	 * Creates a table which acts as a Map to store <String, Serializable> pairs of objects.
	 * While this method will not enforce Serializability of the objects, it is crucial that it implement Serializable.
	 * 
	 * Will create the table in the database if it does not exist
	 * 
	 * @param tableName		Name of table
	 * @param type			Type of object that will be stored
	 * @return				SQLTable object representing the given table
	 */
	public synchronized <V> SQLTable<V> getTable(String tableName, Class<V> type)
	{
		return new SQLTable<V>(tableName, this);
	}
	/** Executes the specified SQL request */
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
	/** Executes the specified SQL update. Will throw an SQLException so it can be handled*/
	public synchronized void executeUpdateCatchable(String request) throws SQLException
	{
		statement.executeUpdate(request);
	}
	/** Executes the specified SQL query */
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
	/** Executes the specified SQL query. Will throw an SQLException so it can be handled*/
	public synchronized ResultSet executeQueryCatchable(String request) throws SQLException
	{
		return statement.executeQuery(request);
	}
}
package database;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SQLTable <V> 
{
	private static final String COL_NAME = "COL_NAME";
	private static final String COL_BLOB = "COL_BLOB";
	
	private String table; //table name
	private SQLDatabase database;
	SQLTable(String table, SQLDatabase database)
	{
		this.table = table;
		this.database = database;
		checkTable();
	}
	
	private void checkTable()
	{
		try
		{
			database.executeUpdateCatchable("create table "+table+"("
					+ COL_NAME + " varchar(64), "
					+ COL_BLOB + " blob"
					+ ");");
		}
		catch(SQLException e)
		{
			//Table exists
		}
	}
	/**
	 * 
	 * Stores the object in the table hashed by name.
	 * 
	 * Will overwrite any previous entry stored by the same name
	 * 
	 * @param name		Name of object to store it by (for simpler retrieval)
	 * @param object	Object to store (must be Serializable)
	 *
	 */
	public synchronized void put(String name, V object)
	{
		try
		{
			database.executeUpdate("DELETE FROM "+table+" WHERE "+COL_NAME+" = \""+name+"\"");
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + table + " VALUES ('"+name+"', ?)");
			Blob thisBlob = database.getConnection().createBlob();
			OutputStream stream = thisBlob.setBinaryStream(1);
			Serializer.writeTo((Serializable) object, stream);
			ps.setBlob(1, thisBlob);
			ps.executeUpdate();
		}
		catch(IOException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/** 
	 * 
	 * Retrieves the object in the table hashed by name.
	 * 
	 * NOTE: The interface differs slightly from java.util.Map as it returns null if the key is not found
	 * rather than throw an exception
	 * 
	 * @param name		Name of object to retrieve
	 * @return			Object specified by table and name. Returns null if no such object was found
	 *
	 */
	@SuppressWarnings("unchecked")
	public synchronized V get(String name)
	{
		try
		{
			ResultSet rs = database.executeQuery("select * from "+table+" where "+COL_NAME+" = \""+name+"\"");
			if(rs.next()) //only one entry expected
			{
				return (V) Serializer.objectFrom(rs.getBlob(COL_BLOB).getBinaryStream());
			}
		}
		catch(IOException | SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/** 
	 * 
	 * Deletes the object in the table hashed by name.
	 * 
	 * @param name		Name of object to delete
	 *
	 */
	public synchronized void remove(String name)
	{
		database.executeUpdate("delete from "+table+" where "+COL_NAME+" = \""+name+"\"");
	}
	public synchronized void removeAll()
	{
		database.executeUpdate("delete from "+table);
	}
	/**
	 * 
	 * Returns true if the key exists in the specified table
	 * 
	 * @param key		Which key to find
	 * @return			Whether the key exists in the specified table
	 */
	public synchronized boolean containsKey(String key)
	{
		try
		{
			ResultSet rs = database.executeQuery("select * from "+table+" where "+COL_NAME+" = \""+ key +"\"");
			return rs.next();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 
	 * Returns a Set<String> of all names of all objects stored in this table
	 * 
	 * @return			A list of all the names used to store objects
	 */
	public synchronized Set<String> keySet()
	{
		Set<String> names = new HashSet<>();
		try
		{
			ResultSet rs = database.executeQuery("select * from "+table);
			while(rs.next())
			{
				names.add(rs.getString(COL_NAME));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return names;
	}
	
	/**
	 * 
	 * Returns a Collection<V> of all objects stored in this table
	 * 
	 * @return			A collection of all objects
	 */
	@SuppressWarnings("unchecked")
	public synchronized Collection<V> values()
	{
		Collection<V> names = new HashSet<>();
		try
		{
			ResultSet rs = database.executeQuery("select * from "+table);
			while(rs.next())
			{
				names.add((V) Serializer.objectFrom(rs.getBlob(COL_BLOB).getBinaryStream()));
			}
		}
		catch(IOException | SQLException e)
		{
			e.printStackTrace();
		}
		return names;
	}
	
	/**
	 * 
	 * @return		Returns true if the table is empty
	 */
	public synchronized boolean isEmpty()
	{
		return keySet().isEmpty();
	}
}

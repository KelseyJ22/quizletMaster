package database;

public class SQLUtils 
{
	/** Puts single quotes around the string, and replaces quotes with the sql version of the single quote */
	public static String format(String s)
	{
		s.replace("'", "''");
		s.replace("\\", "\\\\");
		return "'"+s+"'";
	}
}

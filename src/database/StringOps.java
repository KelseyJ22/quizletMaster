package database;

public class StringOps 
{
	//Number formatting (Could use Formatter)
	public static String roundDown2(double d) 
	{
	    return Double.toString((long) (d * 1e2) / 1e2);
	}
	public static String roundDown1(double d) 
	{
	    return Double.toString((long) (d * 1e1) / 1e1);
	}
	/** Searches base string for the first start token, and grabs the string between that and the next occurring end token */
	public static String textBetween(String base, String start, String end)
	{
		return textBetween(base, start, end, 0);
	}
	/** Searches base string for the first start token, starting at an offset, and grabs the string between that and the next occurring end token */
	public static String textBetween(String base, String start, String end, int offset)
	{
		int startIndex = base.indexOf(start, offset) + start.length();
		if(base.indexOf(end, startIndex + 1) >= startIndex)
		{
			return base.substring(startIndex, base.indexOf(end, startIndex + 1));
		}
		return null;
	}
	public static String capitalizeFirstLetterOf(String word)
	{
		return Character.toUpperCase(word.charAt(0)) + word.substring(1);
	}
	
}

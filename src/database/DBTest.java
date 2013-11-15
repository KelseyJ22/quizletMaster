package database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DBTest 
{
	public static void main(String[] args)
	{
		new DBTest().go();
	}
	private void go()
	{
		SQLDatabase database = new SQLDatabase(MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD, MyDBInfo.MYSQL_DATABASE_NAME);
		
		//Class parameter is for some type safety
		SQLTable<SomeObject> table = database.getTable("myTable", SomeObject.class);
		table.put("object1", new SomeObject("Hi"));
		table.put("object1", new SomeObject("Replace Hi"));
		table.put("object2", new SomeObject("Hello"));
		
		SomeObject firstObject = table.get("object1");
		System.out.println(firstObject.toString());
		
		System.out.println("\nmyTable has the following keys:");
		for(String key : table.keySet())
		{
			System.out.println(key);
		}
		
		List<SomeObject> aList = new ArrayList<>();
		aList.add(new SomeObject("I'm in a list!"));
		aList.add(new SomeObject("I'm in a list too!"));
		//table.put("list1", aList); //SQLTable is typesafe :)
		
		//Java generics have their limits...
		@SuppressWarnings("rawtypes")
		SQLTable<List> tableOfLists = database.getTable("myTableOfLists", List.class);
		tableOfLists.put("listName", aList);
		
		//Which means you have to cast it when it comes out
		@SuppressWarnings("unchecked")
		List<SomeObject> iWantMyList = (List<SomeObject>) tableOfLists.get("listName");
		
		System.out.println("\nIn the list:");
		for(SomeObject object : iWantMyList)
		{
			System.out.println(object);
		}
	}
}
class SomeObject implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6557749881286708531L;
	private String data;
	public SomeObject(String data)
	{
		this.data = data;
	}
	@Override
	public String toString()
	{
		return data;
	}
}
package database;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Serializer 
{
	/** 
	 * 
	 * Turns a serializable object into a byte array
	 * 
	 */ 
	public static void writeTo(Serializable object, OutputStream os) throws IOException
	{
		ObjectOutput out = null;
		try 
		{
			out = new ObjectOutputStream(os);   
			out.writeObject(object);
		} 	
		finally 	
		{
			out.close();
		}
	}
	
	/** 
	 * 
	 * Turns an input stream into an object
	 * 
	 * Code from http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
	 * @throws IOException 
	 * 
	 */ 
	public static Object objectFrom(InputStream inputStream) throws IOException
	{
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(inputStream);
		  	return in.readObject(); 
		} 
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			in.close();
		}
		System.err.println("Serialization issue in ");
		return null;
	}
}
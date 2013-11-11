package database;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BlobManager 
{
	/**
	 * Compression and Decompression details:
	 * 
	 * Values are separated by delim variable (which should be an unused phrase) 
	 * The entire blob is prefixed and suffixed by the delim variable
	 * 
	 * Notes: 
	 * The same delim variable must be used to decompress the same blob
	 * DO NOT nest blobs compressed by the same BlobManager within each other. They will fail to decompress if they use the same delimiter
	 * 
	 */
	private String delim;
	public BlobManager(String delim)
	{
		this.delim = delim;
	}
	public String compress(Collection<? extends Storable> set)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(delim);
		for(Storable elem : set)
		{
			builder.append(elem.toSQL() + delim);
		}
		return builder.toString();
	}
	public String compressStringSet(Set<String> set)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(delim);
		for(String elem : set)
		{
			builder.append(elem + delim);
		}
		return builder.toString();
	}
	public Set<String> decompress(String blob)
	{
		Set<String> elems = new HashSet<String>();
		int index = 0;
		while((index = blob.indexOf(delim, index)) != -1)
		{
			if(index == blob.length() - 1) //make sure we're not trying to go off the end of the string
			{
				break;
			}
			elems.add(StringOps.textBetween(blob, delim, delim, index));
			index ++;
		}
		return elems;
	}
	public Set<Integer> decompressIntSet(String blob)
	{
		Set<Integer> elems = new HashSet<>();
		for(String stringElem : decompress(blob))
		{
			elems.add(Integer.parseInt(stringElem));
		}
		return elems;
	}
	public String compress(Map<String, ? extends Storable> map)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(delim);
		for(String key : map.keySet())
		{
			builder.append(key+delim+map.get(key).toSQL()+delim);
		}
		return builder.toString();
	}
	public Map<String, String> decompressMap(String blob)
	{
		Map<String, String> map = new HashMap<String, String>();
		boolean readKey = true;
		String storedKey = "";
		int index = 0;
		while((index = blob.indexOf(delim, index)) != -1)
		{
			if(index == blob.length() - 1)
			{
				break;
			}
			String elem = StringOps.textBetween(blob, delim, delim, index);
			if(readKey)
			{
				storedKey = elem;
			}
			else
			{
				map.put(storedKey, elem);
			}
			readKey = !readKey; //alternate
			index++;
		}
		return map;
	}
}

package org.civex.utils.dataloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Map;


public class CSVDataLoader 
{
	
	private int maxLines=3000;
	final private char delimeter;
	final private char quotes;
	final private boolean useHeader;
	
	public CSVDataLoader()
	{
		this(',',true);
	}
	
	public CSVDataLoader(char delimeter,boolean useHeader)
	{
		this(delimeter,0, useHeader);
	}
	
	public CSVDataLoader(char delimeter, int quotes, boolean useHeader)
	{
		this.delimeter=delimeter;
		this.quotes=(char)quotes;
		this.useHeader=useHeader;
	}
	
public void readURL(String str, Map<String,?> params,final IDataLoaderHandler handler) throws IOException
{
	final StringBuilder sbld=new StringBuilder(str.length()+params.size()*30);
	sbld.append(str);
	sbld.append('?');
	boolean next=false;
	for(final Map.Entry<String, ?> k:params.entrySet())
	{
		if (next) sbld.append('&');
		sbld.append(k.getKey());
		sbld.append('=');
		Object v=k.getValue();
		sbld.append(v!=null?URLEncoder.encode(v.toString()):"");
		next=true;
	}
	readURL(sbld.toString(),handler);
}

/**reads data from URL**/	
public void readURL(String str,final IDataLoaderHandler handler) throws IOException
{
	URL url=new URL(str);
	URLConnection conn=url.openConnection();
	
	InputStream stream=null;
	try
	{
		stream=conn.getInputStream();
		this.readData(stream,handler);
	}
	finally
	{
		stream.close();
	}
}
	
public void readFile(String fpath,final IDataLoaderHandler handler) throws IOException
	{
		final File file=new File(fpath);
		
		FileInputStream fstream=null;
		try
		{
			fstream=new FileInputStream(file);
			readData(fstream,handler);	
		}
		finally
		{
			fstream.close();	
		}
	}
	

public void readData(final InputStream stream,final IDataLoaderHandler handler) throws IOException
{
	 readData(new InputStreamReader(stream),handler);
}
	
	
	
public void readData(Reader reader,final IDataLoaderHandler handler) throws IOException
{
	if (!(reader instanceof BufferedReader)) reader=new BufferedReader(reader);
	readData((BufferedReader)reader, handler);
}
	
public void readData(final BufferedReader reader, final IDataLoaderHandler handler) throws IOException
{
	handler.onStartRead();
	String s=null;
	int lines=-1;
	while((s=reader.readLine())!=null)
	{
		
		if (lines==-1) 
		{
			lines=0;
			if (useHeader)
			{
			 onHeaderRecord(s, handler);
			 continue;
			}
		}
		lines++;
		if (s.trim().length()==0) continue;
		if (lines==maxLines) break;
		try
		{
			onNextRecord(s, lines, handler);
		}
		catch(ParseException x)
		{
			throw new IOException(x);
		}
		    
	}
	handler.onEndRead(lines);
	//accessTimeStamp=System.currentTimeMillis();
}
	
	
protected void onHeaderRecord(final String s,final IDataLoaderHandler handler)
{
	int offStart=0;
	int off=0;
	int col=0;
	do
	{
		off=s.indexOf(delimeter, offStart+1);
		off=off>=0?off:s.length();
		handler.onDeclareField(col,s,offStart,off);
		offStart=off+1;
		col++;
	}
	while(off>=0 && off<s.length());
}

protected void onNextRecord(final String s, final int line,final IDataLoaderHandler handler) throws ParseException
{
	handler.onNextRecord(line);
	int offStart=0;
	int off=0;
	int col=0;
	do
	{
		off=s.indexOf(delimeter, offStart+1);
		off=off>=0?off:s.length();
		handler.onSetColumnValue(s,line, col,offStart,off);
		offStart=off+1;
		col++;
	}
	while(off>=0 && off<s.length());
	
}

public void readResource(String s, Class<?> clazz, IDataLoaderHandler handler)
{
	
}

}




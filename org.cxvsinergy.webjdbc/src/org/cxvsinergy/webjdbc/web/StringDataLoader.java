package org.cxvsinergy.webjdbc.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class StringDataLoader 
{
	final private static String fext=".sql";
	
	public  void loadData(Map<String, String> m,File f) throws IOException
	{
		final File files[]=f.isDirectory()?f.listFiles():new File[]{f};
		for (final File file:files)
		{
			if (!file.isFile()) continue;
			String nm=file.getName();
			if (!nm.endsWith(fext)) continue;
			nm=nm.substring(0,nm.length()-4);
			loadFileData(file,nm,m);
		}
	}
	
	
	protected void loadFileData(File f,String name,Map<String, String> m) throws IOException
	{
		final FileReader r=new FileReader(f);
		loadData(r,name,m);
	}
	
	protected void loadData(Reader r,String name,Map<String, String> m) throws IOException
	{
		final BufferedReader rdr=new BufferedReader(r);
		String s=null;
		final StringBuilder bld=new StringBuilder(1000);
		
		while((s=rdr.readLine())!=null)
		{
			if (s.trim().length()==0 || s.startsWith("#")) continue;
			bld.append(s).append(' ');
		}
		m.put(name, bld.toString());
	}
	
	public void parseQueryParams() {};
}

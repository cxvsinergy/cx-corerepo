package org.civex.utils.resources;

/**********************************************************************
 * Copyright (c) 2015 cxvsinergy.org., and others. All rights reserved.
 * Contributors: 
 * 	@author Vitaly Samoilov - Initial implementation 
 * 	e-mail : nikevit@gmail.com
 * project : com.cxvsinergy.utils
 * History:
 * 		
 * 	
 *******************************************************************/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.civex.utils.IObjectResolver;
import org.civex.utils.resources.SimpleResourceDescriptor.UrlResource;

public class UrlResourceLoader extends ResourceLoader
{
	private static ResourceLoader eInstance = new UrlResourceLoader();

	public static ResourceLoader getDefaultResourceLoader()
	{
		return eInstance;
	}

	protected static final int BUFF_SIZE = 4096;
	protected static final String DEFAULT_ENCODING = "UTF-8";
	protected static final String COMMENT_PFX="#";

	public void writeContent(IResourceDescriptor desc, String str) throws IOException
	{
		writeContent(desc, new StringReader(str));
	}

	@Override
	public void writeContent(IResourceDescriptor desc, Reader rdr) throws IOException
	{
		if (!isWriteableResource(desc)) throw new IOException("Resource can not be written. " + desc);
		final File f = toLocalFile(desc);
		if (f == null) throw new IOException("Resource can be written. " + desc);
		final FileWriter wr = new FileWriter(f);
		try
		{
			final char buff[] = new char[BUFF_SIZE];
			int sz = 0;
			while ((sz = rdr.read(buff)) > -1)
			{
				wr.write(buff, 0, sz);
			}
		}
		finally
		{
			wr.close();
		}

	}

	@Override
	public void loadContent(final IResourceDescriptor desc, OutputStream stream) throws IOException
	{
		final URL url = new URL(desc.getResourceUri());
		final InputStream ins = url.openStream();
		final byte buff[] = new byte[BUFF_SIZE * 2];
		int sz = 0;
		while ((sz = ins.read(buff)) > -1)
		{
			stream.write(buff, 0, sz);
		}
	}

	@Override
	public void loadContent(IResourceDescriptor desc, Writer wr) throws IOException
	{
		final URL url = new URL(desc.getResourceUri());
		final InputStream ins = url.openStream();
		final byte buff[] = new byte[BUFF_SIZE];
		int sz = 0;
		while ((sz = ins.read(buff)) > -1)
		{
			wr.write(new String(buff, 0, sz));
		}
	}

	@Override
	public boolean requestResourceList(final IResourceDescriptor desc, final List<IResourceDescriptor> coll)
	{
		final File file = toLocalFile(desc);
		if (file == null) return false;
		final File[] files = file.listFiles();
		boolean r = false;
		for (final File f : files)
		{
			r = true;
			if (!f.isFile() && !filterFile(f)) continue;
			try
			{
				coll.add(new UrlResource(f));
			}
			catch (IOException x)
			{
				fireResourceAccessException(x);
			}

		}
		return r;
	}

	protected boolean filterFile(File f)
	{
		return true;
	}

	@Override
	public boolean requestEntriesList(IResourceDescriptor desc, List<IResourceDescriptor> coll)
	{
		final File file = toLocalFile(desc);
		if (file == null) return false;
		final File[] files = file.listFiles();
		boolean r = false;
		for (final File f : files)
		{
			r = true;
			if (!f.isDirectory()) continue;
			try
			{
				coll.add(new UrlResource(f));
			}
			catch (IOException x)
			{
				fireResourceAccessException(x);
			}

		}
		return r;
	}

	@Override
	public boolean isReadableResource(IResourceDescriptor desc)
	{
		return true;
	}

	@Override
	public boolean isWriteableResource(IResourceDescriptor desc)
	{
		final File fl = toLocalFile(desc);
		return fl != null && fl.canWrite();
	}

	
	protected BufferedReader toBufferedReader(IResourceDescriptor desc) throws IOException
	{
		final URL url = new URL(desc.getResourceUri());
		return new BufferedReader(new InputStreamReader(url.openStream()));
	}
	
	
	protected File toLocalFile(IResourceDescriptor desc)
	{
		try
		{
			final URL url = new URL(desc.getResourceUri());
			if (!"file".equals(url.getProtocol())) return null;
		}
		catch (IOException x)
		{
			return null;
		}
		return null;
	}

	@Override
	public IResourceDescriptor createResourceDescriptor(String uri) throws IOException
	{
		return new UrlResource(uri);
	}

	
	
	public  void loadRecordContent(final IResourceDescriptor desc, final IObjectResolver<IResourceDescriptor,Integer,CharSequence> resolver) throws IOException
	{
		
		final BufferedReader reader=toBufferedReader(desc);
		int line=0;
		String s=null;
		while((s=reader.readLine())!=null)
		{
		 resolver.upsertObjectValue(desc, line, s);
		}
	}
	


	public boolean loadStringContent(final IResourceDescriptor desc, final StringBuilder bld) throws IOException
	{
		StringWriter wr=new StringWriter();
		loadContent(desc, wr);
		bld.append(wr.toString());
		return true;
		
	}

	public static String readFileAsString(String path) throws IOException
	{
		StringBuilder r=new StringBuilder(BUFF_SIZE);
		readStreamAsString(new FileInputStream(path),r, BUFF_SIZE);
		return r.toString();
	}
	
	

	public static boolean readStreamAsString(final InputStream stream, StringBuilder bld,int sz) throws IOException
	{

		final BufferedReader brdr = new BufferedReader(new InputStreamReader(stream));
		final StringBuilder sr = new StringBuilder(sz);
		String s = null;
		try
		{
			while ((s = brdr.readLine()) != null)
			{
				sr.append(s).append('\n');
			}
		}
		finally
		{
			brdr.close();
		}
		return true;
	}

	/** taken from stackoverflow, param reader **/
	public static Map<String, List<String>> splitQuery(String url) throws UnsupportedEncodingException, IOException
	{
		final URL u = new URL(url);
		final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		final String[] pairs = u.getQuery().split("&");
		for (String pair : pairs)
		{
			final int idx = pair.indexOf("=");
			final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), DEFAULT_ENCODING) : pair;
			if (!query_pairs.containsKey(key))
			{
				query_pairs.put(key, new ArrayList<String>());
			}
			final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1),
					DEFAULT_ENCODING) : null;
			query_pairs.get(key).add(value);
		}
		return query_pairs;
	}
	
	
	public static void readListContent(final IResourceDescriptor desc, Map<String, String> params) throws IOException
	{

	}
	
	public static void readPropertyMapContent(final IResourceDescriptor desc, Map<String, String> params) throws IOException
	{

	}
}

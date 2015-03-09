package org.civex.utils.app;

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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class AppUtilsHelper
{

	protected static List<String> makeParams(String[] args)
	{
		final List<String> params = new ArrayList<String>(20);
		for (final String arg : args)
			params.add(arg);
		return params;
	}

	public static boolean hasParam(List<String> params, String name)
	{
		final int index = params.indexOf("-" + name);
		return index >= 0;
	}

	public static double resolveCommandParam(List<String> params, String name, double defaultValue)
	{
		String v = resolveCommandParam(params, name, null);
		return v == null ? defaultValue : Double.parseDouble(v);
	}

	public static int resolveCommandParam(List<String> params, String name, int defaultValue)
	{
		final String v = resolveCommandParam(params, name, null);
		return v == null ? defaultValue : Integer.parseInt(v);
	}

	public static String resolveCommandParam(List<String> params, String name, String defaultValue)
	{
		final int index = params.indexOf("-" + name);
		if (index < 0 || index == params.size() - 1)
			return defaultValue;
		return params.get(index + 1);
	}

	public static String resolveCommandParam(Map<String, ?> params, String name, String defaultValue)
	{
		final Object v = params.get(name);
		return v != null ? v.toString() : defaultValue;
	}

	public static int resolveCommandParam(Map<String, ?> params, String name, int defaultValue)
	{
		final String v = resolveCommandParam(params, name, null);
		return v == null ? defaultValue : Integer.parseInt(v);
	}

	public static void safeSleep(long millis)
	{
		try
		{
			Thread.sleep(millis);
		} catch (InterruptedException x)
		{
			x.printStackTrace();
		}
	}

	public static Map<String, ?> list2map(List<String> args)
	{
		final Map<String, Object> m = new HashMap<String, Object>();
		String prev = null;
		for (int ii = 0; ii < args.size(); ii++)
		{
			String v = args.get(ii);
			if (v.startsWith("-"))
			{
				prev = v.substring(1);
				m.put(v, Boolean.TRUE);
				continue;
			}
			if (prev == null)
				continue;
			m.put(prev, v); // adds the last value
		}
		return m;
	}

	public static int parseNumber(String v)
	{
		return Integer.parseInt(v);
	}

	/** copy data from URL to the target file **/
	public static void copyFromURL(final String query, final File target) throws IOException
	{
		final URL url = new URL(query);
		final InputStream in = url.openStream();
		final OutputStream fous = new FileOutputStream(target);
		final byte buff[] = new byte[0x3FF];
		int sz = 0;
		try
		{
			while ((sz = in.read(buff)) >= 0)
			{
				fous.write(buff, 0, sz);
			}
		} finally
		{
			fous.close();
		}
	}

	public static List<String> readCollectionFromParam(List<String> params, String name, String delimeter, String dvalue)
			throws IOException
	{
		String str = resolveCommandParam(params, name, null);
		if (str == null)
			return Collections.emptyList();
		if (str.startsWith("@"))
		{
			str = readFileAsString(str.substring(1));
		}
		final StringTokenizer stoken = new StringTokenizer(str, delimeter);
		final List<String> result = new ArrayList<String>();
		while (stoken.hasMoreTokens())
		{
			result.add(stoken.nextToken());
		}
		return result;
	}

	public static String readFileAsString(String fpath) throws IOException
	{

		final BufferedReader brdr = new BufferedReader(new FileReader(fpath));
		final StringBuilder sr = new StringBuilder(fpath.length());
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
		return sr.toString();
	}
}

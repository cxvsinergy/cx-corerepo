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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;


public class SimpleResourceDescriptor<T> implements IResourceDescriptor
{
	protected String uri;	
	
	public SimpleResourceDescriptor(String uri)
	{
		this(uri,null);
	}
	
	
	public SimpleResourceDescriptor(String uri, Map<String,?> attribs)
	{
		this.uri=uri;
	}
	
	@Override
	public IResourceDescriptor getLocalResource()
	{
		return null;
	}

	@Override
	public String getResourceId()
	{
		return null;
	}

	@Override
	public String getResourceUri()
	{
		return uri;
	}

	@Override
	public long getResourceSize()
	{
		return -1;
	}

	@Override
	public long getResourceTimestamp()
	{
		return 0;
	}

	@Override
	public String getResourceVersion()
	{
		return null;
	}
	
	@Override
	public Map<String, ?> getResourceAttributes()
	{
		return Collections.emptyMap();
	}


	public T getInternalResourceImpl() {return null;}
	
	
	
	@Override
	public int hashCode()
	{
		return 31+uri.hashCode();
	}


	@Override
	public boolean equals(Object obj)
	{
		final IResourceDescriptor other=(IResourceDescriptor)obj;
		return getResourceUri().equals(other.getResourceUri());
	}


	@Override
	public String toString()
	{
		return getResourceUri();
	}


	/**Note, this decoder does not support encoding/decoding**/
	public static class UrlResource extends SimpleResourceDescriptor<URL>
	{
		final private URL url;
		
		public UrlResource(File f) throws IOException
		{
			this(f.toURI().toString());
		}
		
		public UrlResource(String uri) throws IOException
		{
			super(uri);
			this.url=new URL(uri);
		}

		@Override
		public URL getInternalResourceImpl()
		{
			return url;
		}

		@Override
		public String getResourceId()
		{
			String n=uri;
			int v1=uri.indexOf('?');
			if (v1>0) n=n.substring(0, v1-1);
			int v2=uri.lastIndexOf('/');
			if (v2>0) n=n.substring(v2+1);
			return n;
		}
		
		public String getUrlParam(String name) { return null;}
		
		
		public void setUrlParam(String name, String value) 
		{ 
			String qstr=getUrlQueryString();
			//if (qstr==null) qstr="";
			
			return;
		}
		
		public void setUrlQueryString(String qstr) 
		{
			final int v=uri.indexOf('?');
			uri=toUriString(this)+'?'+qstr;
			return;
		
		}
		
		public String getUrlQueryString()
		{
			final int v=uri.indexOf('?');
			return v<0?null:uri.substring(v+1);			
		}
		
		
		public static String toUriString(UrlResource r)
		{
			final int v=r.uri.indexOf('?');
			return v<0?r.uri:r.uri.substring(0,v);
		}
		
		
		
		
	}
}

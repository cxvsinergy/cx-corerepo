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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.util.List;

import org.civex.utils.resources.SimpleResourceDescriptor.UrlResource;

public class UrlResourceLoader extends ResourceLoader
{
	private static ResourceLoader eInstance=new UrlResourceLoader();
	public static ResourceLoader getDefaultResourceLoader() {return eInstance;}
	
	@Override
	public void loadContent(IResourceDescriptor desc, OutputStream stream) throws IOException
	{
		final URL url=new URL(desc.getResourceUri());
		final InputStream ins=url.openStream();
		final byte buff[]=new byte[0x0ffff];
		int sz=0;
		while((sz=ins.read(buff))>-1)
		{
			stream.write(buff,0,sz);
		}
	}

	@Override
	public void loadContent(IResourceDescriptor desc, Writer wr) throws IOException
	{
		final URL url=new URL(desc.getResourceUri());
		final InputStream ins=url.openStream();
		final byte buff[]=new byte[4096];
		int sz=0;
		while((sz=ins.read(buff))>-1)
		{
			wr.write(new String(buff, 0, sz));
		}
	}

	

	@Override
	public boolean requestResourceList(final IResourceDescriptor desc, final List<IResourceDescriptor> coll)
	{
		final File file=toLocalFile(desc);
		if (file==null) return false;
		final File[] files=file.listFiles();
		boolean r=false;
		for (final File f:files)
		{
			r=true;
			if (!f.isFile() && !filterFile(f)) continue;
			try
			{
				coll.add(new UrlResource(f));	
			}
			catch(IOException x)
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
		final File file=toLocalFile(desc);
		if (file==null) return false;
		final File[] files=file.listFiles();
		boolean r=false;
		for (final File f:files)
		{
			r=true;
			if (!f.isDirectory()) continue;
			try
			{
				coll.add(new UrlResource(f));	
			}
			catch(IOException x)
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
		final File fl=toLocalFile(desc);
		return fl!=null && fl.canWrite();
	}

	
	protected File toLocalFile(IResourceDescriptor desc)
	{
		try
		{
			final URL url=new URL(desc.getResourceUri());
			if(!"file".equals(url.getProtocol())) return null;
		}
		catch(IOException x)
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

}

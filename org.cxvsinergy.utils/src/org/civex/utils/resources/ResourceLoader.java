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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class ResourceLoader implements IResourceLoader
{

	@Override
	public void loadContent(IResourceDescriptor desc, OutputStream stream) throws IOException
	{

	}

	@Override
	public void loadContent(IResourceDescriptor desc, Writer wr) throws IOException
	{
		
	}

	@Override
	public void writeContent(IResourceDescriptor desc, Object rdr) throws IOException
	{
		
	}

	@Override
	public void writeContent(IResourceDescriptor desc, Reader rdr) throws IOException
	{
		
	}

	@Override
	public boolean requestResourceList(final IResourceDescriptor desc, final List<IResourceDescriptor> coll)
	{
		return false;
		
	}

	@Override
	public Map<String, ?> getResourceAttributes(IResourceDescriptor desc)
	{
		return desc.getResourceAttributes();
	}

	@Override
	public boolean requestEntriesList(IResourceDescriptor desc, List<IResourceDescriptor> coll)
	{
		return false;
	}

	@Override
	public IResourceDescriptor requestLocalResource(IResourceDescriptor desc)
	{
		return null;
	}

	@Override
	public boolean isReadableResource(IResourceDescriptor desc)
	{
		return true;
	}

	@Override
	public boolean isWriteableResource(IResourceDescriptor desc)
	{
		return false;
	}


	protected void fireResourceAccessException(IOException x)
	{
		
	}

	public IResourceDescriptor createResourceDescriptor(String uri) throws IOException
	{

		return null;
	}
	
	
	public static void  loadContent(IResourceLoader loader,IResourceDescriptor desc, File f) throws IOException
	{
		loader.loadContent(desc, new FileOutputStream(f));
	}
	
	
	public static void  loadFileContent(IResourceLoader loader,IResourceDescriptor desc, File base, String fname) throws IOException
	{
		File f=new File(base,fname);
		loader.loadContent(desc, new FileOutputStream(f));
	}
}

package org.civex.utils.resources;

import java.io.File;

import org.civex.utils.IObjectFilter;

public class FileExtensionFilter<M> implements IObjectFilter<M,File>
{

	@Override
	public boolean filter(M model, File file) 
	{
		if (!file.isFile())  return false;
		final String name=file.getName();
		int idx=name.lastIndexOf('.');
		if (idx<0) return false;
		return true;
	}

	@Override
	public boolean filter(File file) 
	{
		return filter(null, file);
	}

	@Override
	public void reset() 
	{
		
	}

}

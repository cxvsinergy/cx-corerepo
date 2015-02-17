package org.civex.utils.resources;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator<File>
{
	public static final Comparator<File> DIRECT=new FileComparator();
	
	@Override
	public int compare(final File f1, final File f2) 
	{
		if (f1==f2) return 0;
		return f1.getName().compareToIgnoreCase(f1.getName());
	}

}

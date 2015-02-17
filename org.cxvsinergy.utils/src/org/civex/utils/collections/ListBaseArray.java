package org.civex.utils.collections;

import java.util.List;

public class ListBaseArray 
{
	private List<?> l;
	public 	int arr[];
	private int off=0;
	private int sz;
	private boolean dirty;
	
	public ListBaseArray(List<?> l)
	{
		this.l=l;
		invalidate();
	}
	
		
	public int size()
	{
		return l.size();
	}
	
	public void invalidate()
	{
		sz=l.size();
		arr=new int[sz];
		off=0;
		markDirty();
	}
	
	
	public boolean isDirty() {return dirty;}
	public void markDirty() {dirty=true;}
	
	public void remove(int index)
	{
		invalidate();
	}
	
	public void upsert(int index)
	{
		invalidate();
	}
}
